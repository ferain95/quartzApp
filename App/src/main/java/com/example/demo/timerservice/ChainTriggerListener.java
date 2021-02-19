//package com.example.demo.timerservice;
//
//import com.example.demo.info.TimerInfo;
//import com.example.demo.workspace.WorkspaceService;
//import org.quartz.JobDataMap;
//import org.quartz.JobExecutionContext;
//import org.quartz.Trigger;
//import org.quartz.TriggerListener;
//
//public class ChainTriggerListener implements TriggerListener {
//    //if Print Hello World Job is done, execute next Job
//
//    private final SchedulerService schedulerService;
//
//    private static int counter = 1;
//
//    public ChainTriggerListener(SchedulerService schedulerService) {
//        this.schedulerService = schedulerService;
//    }
//
//    @Override
//    public String getName() {
//        return ChainTriggerListener.class.getSimpleName();
//    }
//
//    @Override
//    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
//        final String timerId = trigger.getKey().getName();
//        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
//        TimerInfo info = (TimerInfo)jobDataMap.get(timerId);
//
//        if (info.getRemainingFireCount() == 0 && counter == 1) {
//            counter = 0;
//            schedulerService.runNextJob();
//        }
//    }
//
//    @Override
//    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
//        return false;
//    }
//
//    @Override
//    public void triggerMisfired(Trigger trigger) {
//
//    }
//
//    @Override
//    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
//
//    }
//}
