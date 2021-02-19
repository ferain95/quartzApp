//package com.example.demo.workspace;
//
//import com.example.demo.info.CronInfo;
//import com.example.demo.jobs.PrintTimeJob;
//import com.example.demo.timerservice.CronScheduleService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class WorkspaceCronService {
//    private final CronScheduleService cScheduler;
//
//    public WorkspaceCronService(final CronScheduleService cScheduler) {
//        this.cScheduler = cScheduler;
//    }
//
//    public void cronTriggerJob() {
//        final CronInfo info = new CronInfo();
//        info.setSchedule("0 0/1 15 * * ?");
//        System.out.println("Okay, running");
//        cScheduler.schedule(PrintTimeJob.class, info);
//    }
//
//}
