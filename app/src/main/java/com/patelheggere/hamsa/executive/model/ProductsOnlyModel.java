package com.patelheggere.hamsa.executive.model;

public class ProductsOnlyModel {
    private String name;
    private String pid;

    public ProductsOnlyModel() {
    }

    public ProductsOnlyModel(String name, String pid) {
        this.name = name;
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
