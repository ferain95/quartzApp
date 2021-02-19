package com.example.demo.info;

import java.io.Serializable;

public class CronInfo implements Serializable {
    private String callbackData;
    private String schedule;

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule){
        this.schedule = schedule;
    }

    public String getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }



}
