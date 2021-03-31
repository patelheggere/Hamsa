package com.patelheggere.hamsa.executive.model;

public class ProductsOnlyModel {
    private String name;
    private String id;

    public ProductsOnlyModel() {
    }

    public ProductsOnlyModel(String name, String pid) {
        this.name = name;
        this.id = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return id;
    }

    public void setPid(String pid) {
        this.id = pid;
    }
}
