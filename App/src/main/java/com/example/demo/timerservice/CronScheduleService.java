package com.example.demo.timerservice;


import com.example.demo.info.CronInfo;
import com.example.demo.jobs.PrintHelloWorldJob;
import com.example.demo.jobs.PrintTimeJob;
import com.example.demo.util.TimerUtil;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class CronScheduleService {

    private static final Logger LOG = LoggerFactory.getLogger(PrintHelloWorldJob.class);
    private final Scheduler scheduler;


    public CronScheduleService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    //for cron triggers
    public void schedule(final Class jobClass, final CronInfo info) {
        final JobDetail jobDetail = TimerUtil.buildJobDetail(jobClass, info);
        final Trigger trigger = TimerUtil.buildTrigger(jobClass, info);

        try {
            if (scheduler.checkExists(jobDetail.getKey())){
                scheduler.deleteJob(jobDetail.getKey());
            }
            System.out.println("I made it here &@#$@#$@");
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @PostConstruct
    public void init(){
        try {
            scheduler.start();
            System.out.println("I made it here@@");
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

}
