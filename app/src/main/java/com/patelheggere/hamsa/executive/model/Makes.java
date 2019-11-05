package com.patelheggere.hamsa.executive.model;

class Makes {
    private String pid;
    private String name;
    private String mkID;

    public Makes(String pid, String name, String mkID) {
        this.pid = pid;
        this.name = name;
        this.mkID = mkID;
    }

    public Makes() {
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

    public String getMkID() {
        return mkID;
    }

    public void setMkID(String mkID) {
        this.mkID = mkID;
    }
}
