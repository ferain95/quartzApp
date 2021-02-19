package com.example.demo.timerservice;

import com.example.demo.info.TimerInfo;
import com.example.demo.jobs.PrintHelloWorldJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

public class SimpleTriggerListener implements TriggerListener {

    private final SchedulerService schedulerService;
    private static int counter;

    public SimpleTriggerListener(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @Override
    public String getName() {
        return SimpleTriggerListener.class.getSimpleName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {

        if(trigger.getKey().getName().equals("PrintHelloWorldJob")) {
            final String timerId = trigger.getKey().getName();
            JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();

            TimerInfo info = (TimerInfo) jobDataMap.get(timerId);

            if (!info.isRunForever()) {
                int remainingFireCount = info.getRemainingFireCount() - 1;
//                String s = String.format("Reached here! %s", counter++);
//                System.out.println(s);
                info.setRemainingFireCount(remainingFireCount);
                schedulerService.updateTimer(timerId, info);
                if (remainingFireCount == 0) {
                    schedulerService.runNextJob();
                }
            }
        }

        if(trigger.getKey().getName().equals("PrintTimeJob")){
            System.out.println("Success!");
        }
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {

    }
}
