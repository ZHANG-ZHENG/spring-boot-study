package com;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.util.RobotUtil;

import java.awt.AWTException;
import java.awt.Robot;

//import domain.util.YmlConfig;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Spring Boot 应用启动类
 *
 * @author : liangxifeng
 * @date : 2018-1-19
 */
// Spring Boot 应用的标识
@SpringBootApplication
//如果mybatis中service实现类中加入事务注解，需要此处添加该注解
//@EnableTransactionManagement
// mapper 接口类扫描包配置
//@MapperScan("domain.dao")
public class AdverCenterApplication  {//extends SpringBootServletInitializer
    //读取配置文件
//    @Autowired
//    private YmlConfig ymlConfig;
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(AdverCenterApplication.class);
//    }

    public static void main(String[] args) {
        //SpringApplication.run(AdverCenterApplication.class, args);
    	SpringApplicationBuilder builder = new SpringApplicationBuilder(AdverCenterApplication.class);
        builder.headless(false).run(args);
        //RobotUtil.getInstance();
    }

    /**
     * 注册netty-socketio服务端
     * @author liangxifeng 2018-07-07
     * @return
     */
    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();

        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){   //在本地window环境测试时用localhost
            System.out.println("this is  windows");
            config.setHostname("localhost");
        } else {
            config.setHostname("192.168.9.209");
        }
        config.setPort(9092);

        /*config.setAuthorizationListener(new AuthorizationListener() {//类似过滤器
            @Override
            public boolean isAuthorized(HandshakeData data) {
                //http://localhost:8081?username=test&password=test
                //例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证
                // String username = data.getSingleUrlParam("username");
                // String password = data.getSingleUrlParam("password");
                return true;
            }
        });*/

        final SocketIOServer server = new SocketIOServer(config);
        return server;
    }

    /**
     * tomcat启动时候，扫码socket服务器并注册
     * @param socketServer
     * @return
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }
      
}
