package com.patelheggere.hamsa.executive.model;

class EQType {
    private String pid;
    private String name;
    private String EQID;

    public EQType() {
    }

    public EQType(String pid, String name, String EQID) {
        this.pid = pid;
        this.name = name;
        this.EQID = EQID;
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

    public String getEQID() {
        return EQID;
    }

    public void setEQID(String EQID) {
        this.EQID = EQID;
    }
}
