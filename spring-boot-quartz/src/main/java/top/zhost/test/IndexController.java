package top.zhost.test;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	public static int count =0;

    @RequestMapping(value = "/test")
    public String index() {
    	count++;
    	try {
            // 1、创建调度器Scheduler
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
            JobDetail jobDetail = JobBuilder.newJob(QuartzJob1.class)
                                            .withIdentity("job"+count, "group1")
                                            .usingJobData("test","hello myjob1")
                                            .build();      
            
    		//获取距离当前时间3秒后的时间
            Date startDate = new Date();
            startDate.setTime(startDate.getTime()+3000);
    		//获取距离当前时间6秒后的时间
//    		Date endDate=new Date();
//    		endDate.setTime(endDate.getTime()+16000);
    		
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+count,"triggerGroup1")
            		.startAt(startDate)//.endAt(endDate)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(10, 1))
                    .build();//一直执行

            //4、执行
            scheduler.scheduleJob(jobDetail, trigger);
            System.out.println("--------scheduler start ! ------------");
            scheduler.start();		
           
            //scheduler.interrupt(jobDetail.getKey());
		} catch (Exception e) {
			e.printStackTrace();
		}


        
        return "Hello World spring-boot-study-demo!!!";
    }
}
