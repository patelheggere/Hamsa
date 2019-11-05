package com.patelheggere.hamsa.executive.model;

public class ProductDetails {
    private String makename;
    private String modelname;
    private String name;

    public ProductDetails() {
    }

    public ProductDetails(String makename, String modelname, String name) {
        this.makename = makename;
        this.modelname = modelname;
        this.name = name;
    }

    public String getMakename() {
        return makename;
    }

    public void setMakename(String makename) {
        this.makename = makename;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
