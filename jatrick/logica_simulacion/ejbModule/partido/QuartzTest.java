package partido;

import java.util.Timer;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;


@Startup
@Singleton
public class QuartzTest {



//	@PostConstruct
//	@Schedule(hour="*", minute="*", second="*/5", persistent=false)
//	public void run() {
//		System.out.println("llevetete!");
//	}
//	
//	@Timeout
//	public void timeout(Timer timer) {
//	    System.out.println("TimerBean: timeout occurred");
//	}


	//	public static void main(String[] args) {
	//
	//		try {
	//			SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
	//
	//			  Scheduler sched = schedFact.getScheduler();
	//
	//			  sched.start();
	//
	//			  // define the job and tie it to our HelloJob class
	//			  JobDetail job = newJob(HelloJob.class)
	//			      .withIdentity("myJob", "group1") // name "myJob", group "group1"
	//			      .build();
	//			        
	//			  // Trigger the job to run now, and then every 40 seconds
	//			  Trigger trigger = newTrigger()
	//			      .withIdentity("myTrigger", "group1")
	//			      .startNow()
	//			      .withSchedule(simpleSchedule()
	//			          .withIntervalInSeconds(20)
	//			          .repeatForever())            
	//			      .build();
	//			  
	//			  Trigger trigger2 = newTrigger()
	//				      .withIdentity("myTrigger2", "group2")
	//				      .startNow()
	//				      .withSchedule(simpleSchedule()
	//				          .withIntervalInSeconds(20)
	//				          .repeatForever())            
	//				      .build();
	//			  Trigger trigger3 = newTrigger()
	//				      .withIdentity("myTrigger3", "group3")
	//				      .startNow()
	//				      .withSchedule(simpleSchedule()
	//				          .withIntervalInSeconds(20)
	//				          .repeatForever())            
	//				      .build();
	//			  Trigger trigger4 = newTrigger()
	//				      .withIdentity("myTrigger4", "group4")
	//				      .startNow()
	//				      .withSchedule(simpleSchedule()
	//				          .withIntervalInSeconds(20)
	//				          .repeatForever())            
	//				      .build();
	//			  Trigger trigger5 = newTrigger()
	//				      .withIdentity("myTrigger5", "group5")
	//				      .startNow()
	//				      .withSchedule(simpleSchedule()
	//				          .withIntervalInSeconds(20)
	//				          .repeatForever())            
	//				      .build();
	//
	//			  // Tell quartz to schedule the job using our trigger
	////			  sched.addJob(job, true);
	//			  sched.scheduleJob(job,trigger);
	////			  sched.scheduleJob(trigger2);
	////			  sched.scheduleJob(trigger3);
	////			  sched.scheduleJob(trigger4);
	////			  sched.scheduleJob(trigger5);
	//
	//		} catch (SchedulerException se) {
	//			se.printStackTrace();
	//		}
	//	}
}
