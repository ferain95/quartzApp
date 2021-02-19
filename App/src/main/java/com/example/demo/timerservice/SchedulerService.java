package com.example.demo.timerservice;

import com.example.demo.info.CronInfo;
import com.example.demo.info.TimerInfo;
import com.example.demo.jobs.LaughingJob;
import com.example.demo.jobs.PrintHelloWorldJob;
import com.example.demo.util.TimerUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class SchedulerService {

    private static final Logger LOG = LoggerFactory.getLogger(PrintHelloWorldJob.class);

    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void schedule(final Class jobClass, final TimerInfo info){
        final JobDetail jobDetail = TimerUtil.buildJobDetail(jobClass, info);
        final Trigger trigger = TimerUtil.buildTrigger(jobClass, info);

        try {
            if (scheduler.checkExists(jobDetail.getKey())){
                scheduler.deleteJob(jobDetail.getKey());
            }
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void updateTimer(final String timerId, final TimerInfo info){
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(timerId));
            if (jobDetail == null) {
                LOG.error("Failed to find timer with id: {}", timerId);
            }
            jobDetail.getJobDataMap().put(timerId, info);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void runNextJob(){
        final TimerInfo info = new TimerInfo();
        info.setRunForever(true);
        info.setRepeatIntervalMs(1000);
        info.setInitialSetOffMs(1000);
        info.setCallbackData("Second job");
//        final JobDetail jobDetail = TimerUtil.buildJobDetail(LaughingJob.class, info);
//        final Trigger trigger = TimerUtil.buildTrigger(LaughingJob.class, info);

        schedule(LaughingJob.class, info);
    }

    @PostConstruct
    public void init(){
        try {
            scheduler.start();
//            System.out.println("im actually here!");
            scheduler.getListenerManager().addTriggerListener(new SimpleTriggerListener(this));
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void preDestroy(){
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public Boolean deleteTimer(String timerId) {
        try{
            return scheduler.deleteJob(new JobKey(timerId));
        } catch (SchedulerException e){
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

}
