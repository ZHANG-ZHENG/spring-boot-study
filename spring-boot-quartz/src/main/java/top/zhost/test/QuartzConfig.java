package top.zhost.test;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

//    @Bean
//    public JobDetail jobDetail1(){
//        return JobBuilder.newJob(QuartzJob1.class).storeDurably().build();
//    }
//    @Bean
//    public Trigger trigger1(){
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(1) //每一秒执行一次
//                .repeatForever(); //永久重复，一直执行下去
//        return TriggerBuilder.newTrigger()
//                .forJob(jobDetail1())
//                .withSchedule(scheduleBuilder)
//                .build();
//    }    
}