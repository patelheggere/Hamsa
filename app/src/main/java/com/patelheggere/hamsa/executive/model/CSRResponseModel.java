package com.patelheggere.hamsa.executive.model;

public class CSRResponseModel {
    private String msg;
    private boolean status;

    public CSRResponseModel(String msg, boolean status) {
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
