package com.patelheggere.hamsa.executive.model;

public class ExecVerifyModel {
    private String name;
    private String id;
    private String phone;
    private boolean status;

    public ExecVerifyModel() {
    }

    public ExecVerifyModel(String name, String id, String phone, boolean status) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
