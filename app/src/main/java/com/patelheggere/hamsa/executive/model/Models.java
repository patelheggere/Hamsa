package com.patelheggere.hamsa.executive.model;

class Models {
    private String pid;
    private String name;
    private String MDID;

    public Models() {
    }

    public Models(String pid, String name, String MDID) {
        this.pid = pid;
        this.name = name;
        this.MDID = MDID;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMDID() {
        return MDID;
    }

    public void setMDID(String MDID) {
        this.MDID = MDID;
    }
}
