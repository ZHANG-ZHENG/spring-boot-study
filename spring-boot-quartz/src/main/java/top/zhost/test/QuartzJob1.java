package top.zhost.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartzJob1 implements Job {
	


	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	JobDetail jobDetail =  jobExecutionContext.getJobDetail();
        Scheduler scheduler = jobExecutionContext.getScheduler();
        JobDataMap jobDataMap =  jobDetail.getJobDataMap();
        System.out.println(jobDataMap.get("test"));
		
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleTrigger st = (SimpleTrigger) jobExecutionContext.getTrigger();
        
        System.out.println("QuartzJob1---- "+st.getTimesTriggered()+" "+ sdf.format(new Date()));
        
        if(st.getTimesTriggered()==5) {

            try {
				//scheduler.interrupt(jobDetail.getKey());
				//scheduler.shutdown(true);
            	 scheduler.deleteJob(jobDetail.getKey());
				System.out.println("QuartzJob stop "+jobDetail.getKey());
			} catch (Exception e) {
				e.printStackTrace();
			}     	
        }		
	}

}
