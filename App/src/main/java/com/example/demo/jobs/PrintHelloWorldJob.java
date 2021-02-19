package com.example.demo.jobs;

import com.example.demo.info.TimerInfo;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintHelloWorldJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(PrintHelloWorldJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        TimerInfo info = (TimerInfo)jobDataMap.get(PrintHelloWorldJob.class.getSimpleName());

        LOG.info("HELLO WORLD");
        LOG.info("Remaining Fire Count: {}", info.getRemainingFireCount());
    }
}
