package com.ete.task;

import java.io.Serializable;

/**
 * monkey 测试任务
 * Created by beishan on 2017/4/15.
 */
public class MonkeyTask implements Serializable{
    private Integer time; //测试时长
    private String appUrl; //app路径
    private long seed; //种子，设置monkey种子

    @Override
    public String toString() {
        return "MonkeyTask{" +
                "time=" + time +
                ", appUrl='" + appUrl + '\'' +
                ", seed=" + seed +
                '}';
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }
}
