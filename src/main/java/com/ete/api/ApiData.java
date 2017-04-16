package com.ete.api;

import java.io.Serializable;

/**
 * Created by beishan on 2017/4/15.
 */
public class ApiData<T> implements Serializable{
    private String type; //任务类型
    private T param;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
