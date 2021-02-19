package com.example.demo.workspace;

import com.example.demo.info.CronInfo;
import com.example.demo.info.TimerInfo;
import com.example.demo.jobs.PrintHelloWorldJob;
import com.example.demo.jobs.PrintTimeJob;
import com.example.demo.timerservice.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService {

    public final SchedulerService scheduler;

    @Autowired
    public WorkspaceService(final SchedulerService scheduler){
        this.scheduler = scheduler;
    }

    public void runPrintHelloWorldJob() {

        final TimerInfo info = new TimerInfo();
//        info.setRunForever(true);
        info.setTotalFireCount(3);
        info.setRemainingFireCount(info.getTotalFireCount());
        info.setRepeatIntervalMs(1000);
        info.setInitialSetOffMs(1000);
        info.setCallbackData("My callback data");

        scheduler.schedule(PrintHelloWorldJob.class, info);
    }

    public Boolean deleteTimer(String timerId){
        return scheduler.deleteTimer(timerId);
    }

    public void printTimeJob() {
        final CronInfo info = new CronInfo();
        //adjust this to set the time where it fires
        info.setSchedule("0/1 47 17 * * ?");
        System.out.println("Okay, running");
        scheduler.schedule(PrintTimeJob.class, info);
    }

}
