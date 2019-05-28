package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;










import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.auth.AuthenticationAccessDeniedHandler;
import com.auth.CustomMetadataSource;
import com.auth.MyPasswordEncoder;
import com.auth.MyUserDetailsService;
import com.auth.UrlAccessDecisionManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //  启用方法级别的权限认证
// 注解开启Spring Security的功能
// WebSecurityConfigurerAdapter:重写它的方法来设置一些web的安全西街
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;
    @Autowired
    CustomMetadataSource metadataSource;
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;
    @Autowired
    AuthenticationAccessDeniedHandler deniedHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**", "/login_p", "/favicon.ico", "/test");
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() // 定义哪些url需要保护，哪些url不需要保护
//				.antMatchers("/", "/message/", "/test").permitAll() // 定义不需要认证就可以访问
//				.anyRequest().authenticated() // 其他地址的访问均需验证权限
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(metadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })		
				.and()
				.formLogin().loginPage("/login_p").loginProcessingUrl("/login") // 定义当需要用户登录时候，转到的登录页面.loginPage("/login_p")
				.usernameParameter("username").passwordParameter("password")
				//.and().failureUrl("/error").permitAll()
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        AuthenticationException e) throws IOException {
                        resp.setContentType("application/json;charset=utf-8");
                        String responseStr =  "";
                        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
                        	responseStr = "账户名或者密码输入错误!";
                        } else if (e instanceof LockedException) {
                        	responseStr = "账户被锁定，请联系管理员!";
                        } else if (e instanceof CredentialsExpiredException) {
                        	responseStr = "密码过期，请联系管理员!";
                        } else if (e instanceof AccountExpiredException) {
                        	responseStr = "账户过期，请联系管理员!";
                        } else if (e instanceof DisabledException) {
                        	responseStr = "账户被禁用，请联系管理员!";
                        } else {
                        	responseStr = "登录失败!";
                        }
                        //resp.setStatus(401);
                        PrintWriter out = resp.getWriter();
                        out.write(responseStr);
                        out.flush();
                        out.close();
                    }
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        Authentication auth) throws IOException {
                        resp.setContentType("application/json;charset=utf-8");

                        //ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write("success");
                        out.flush();
                        out.close();
                    }
                })                
                .permitAll()				
				.and().logout().logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        //ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write("注销成功!");
                        out.flush();
                        out.close();
                    }
                })				
				.permitAll();
		http.csrf().disable().exceptionHandling().accessDeniedHandler(deniedHandler);;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		// auth
		// .inMemoryAuthentication()
		// .withUser("user").password("123456").roles("USER");
	     //在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER
//      auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).
//      withUser("user").password("123456").roles("ADMIN");
	  auth.userDetailsService(myUserDetailsService).passwordEncoder(new MyPasswordEncoder());
	}
}
