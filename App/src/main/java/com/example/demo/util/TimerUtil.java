package com.example.demo.util;


import com.example.demo.info.CronInfo;
import com.example.demo.info.TimerInfo;
import org.quartz.*;

import java.util.Date;

public class TimerUtil {

    private TimerUtil(){}

    public static JobDetail buildJobDetail(final Class jobClass, final TimerInfo info){
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(), info);

        return JobBuilder.newJob(jobClass).withIdentity(jobClass.getSimpleName()).setJobData(jobDataMap).build();

    }

    public static Trigger buildTrigger(final Class jobClass, final TimerInfo info){
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(info.getRepeatIntervalMs());

        if (info.isRunForever()){
            builder = builder.repeatForever();
        } else {
            builder = builder.withRepeatCount(info.getTotalFireCount()-1);
        }

        return TriggerBuilder.newTrigger().withIdentity(jobClass.getSimpleName())
                .withSchedule(builder).startAt(new Date(System.currentTimeMillis()+ info.getInitialSetOffMs()))
                .build();

    }

    public static JobDetail buildJobDetail(final Class jobClass, final CronInfo info){
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(), info);

        return JobBuilder.newJob(jobClass).withIdentity(jobClass.getSimpleName()).setJobData(jobDataMap).build();

    }

    public static Trigger buildTrigger(final Class jobClass, final CronInfo cronInfo) {

        CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(cronInfo.getSchedule());
        return TriggerBuilder.newTrigger()
                .withIdentity(jobClass.getSimpleName()).withSchedule(cronBuilder).forJob(jobClass.getSimpleName())
                .build();

    }

}
