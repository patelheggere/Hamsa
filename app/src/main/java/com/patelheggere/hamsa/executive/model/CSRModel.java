package com.patelheggere.hamsa.executive.model;

public class CSRModel {
    private String custName;
    private String custPhone;
    private String custAdds;
    private String servDetails;
    private String product;
    private String equipmentType;
    private String make;
    private String model;
    private String defects;
    private String engrRemarks;
    private String statusAfterService;
    private String estimation;
    private String total;
    private String mCustID;
    private String ExecID;
    private long dateTimel;
    private String taskID;

    public CSRModel() {
    }

    public CSRModel(String taskID, String custName, String custPhone, String custAdds, String servDetails, String product, String equipmentType, String make, String model, String defects, String engrRemarks, String statusAfterService, String estimation, String total, String mCustID, String execID) {
        this.custName = custName;
        this.custPhone = custPhone;
        this.custAdds = custAdds;
        this.servDetails = servDetails;
        this.product = product;
        this.equipmentType = equipmentType;
        this.make = make;
        this.model = model;
        this.defects = defects;
        this.engrRemarks = engrRemarks;
        this.statusAfterService = statusAfterService;
        this.estimation = estimation;
        this.total = total;
        this.mCustID = mCustID;
        this.ExecID = execID;
        this.taskID = taskID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustAdds() {
        return custAdds;
    }

    public void setCustAdds(String custAdds) {
        this.custAdds = custAdds;
    }

    public String getServDetails() {
        return servDetails;
    }

    public void setServDetails(String servDetails) {
        this.servDetails = servDetails;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDefects() {
        return defects;
    }

    public void setDefects(String defects) {
        this.defects = defects;
    }

    public String getEngrRemarks() {
        return engrRemarks;
    }

    public void setEngrRemarks(String engrRemarks) {
        this.engrRemarks = engrRemarks;
    }

    public String getStatusAfterService() {
        return statusAfterService;
    }

    public void setStatusAfterService(String statusAfterService) {
        this.statusAfterService = statusAfterService;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getmCustID() {
        return mCustID;
    }

    public void setmCustID(String mCustID) {
        this.mCustID = mCustID;
    }

    public String getExecID() {
        return ExecID;
    }

    public void setExecID(String execID) {
        ExecID = execID;
    }

    public long getDateTimel() {
        return dateTimel;
    }

    public void setDateTimel(long dateTimel) {
        this.dateTimel = dateTimel;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }
}
