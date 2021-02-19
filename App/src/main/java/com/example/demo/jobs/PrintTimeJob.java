package com.example.demo.jobs;

import com.example.demo.info.TimerInfo;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintTimeJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(PrintHelloWorldJob.class);
    private static String date = "";
    private static boolean laughingType = true;
    private static boolean extend = true;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy 'at'  HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        LOG.info(sdf.format(date));
    }
}
