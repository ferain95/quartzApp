package com.example.demo.jobs;

import com.example.demo.info.TimerInfo;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LaughingJob implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(PrintHelloWorldJob.class);
    private static String laughing = "";
    private static boolean laughingType = true;
    private static boolean extend = true;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        TimerInfo info = (TimerInfo)jobDataMap.get(LaughingJob.class.getSimpleName());

        if (laughing.length() == 63) {
            extend = false;
        }

        if (extend) {
            String ha = laughingType ? "HA↗" : "HA↘";
            laughingType = !laughingType;
            laughing += ha;
        }

        if (!extend) {
            laughing = laughing.substring(0, laughing.length()-3);
            if (laughing.length() == 3){
                extend = true;
            }
        }

        LOG.info(laughing);
    }
}
