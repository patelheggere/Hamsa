package com.patelheggere.hamsa.executive.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AssignedTasksModel implements Parcelable {
    private String customerName;
    private String exec_id;
    private String mCustAdds, mCustPhone;
    private String serviceDate;
    private String sl_no;
    private String name;
    private String id;
    private String phone;
    private String adds;
    private String assign_cust_id;
    private String task_id;
    private String details;
    private String type;
    private String cust_id;
    private String status;

    public AssignedTasksModel() {
    }

    public AssignedTasksModel(String customerName, String exec_id, String mCustAdds, String mCustPhone, String serviceDate, String sl_no, String name, String id, String phone, String adds, String assign_cust_id, String task_id, String details, String type, String cust_id, String status) {
        this.customerName = customerName;
        this.exec_id = exec_id;
        this.mCustAdds = mCustAdds;
        this.mCustPhone = mCustPhone;
        this.serviceDate = serviceDate;
        this.sl_no = sl_no;
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.adds = adds;
        this.assign_cust_id = assign_cust_id;
        this.task_id = task_id;
        this.details = details;
        this.type = type;
        this.cust_id = cust_id;
        this.status = status;
    }

    protected AssignedTasksModel(Parcel in) {
        customerName = in.readString();
        exec_id = in.readString();
        mCustAdds = in.readString();
        mCustPhone = in.readString();
        serviceDate = in.readString();
        sl_no = in.readString();
        name = in.readString();
        id = in.readString();
        phone = in.readString();
        adds = in.readString();
        assign_cust_id = in.readString();
        task_id = in.readString();
        details = in.readString();
        type = in.readString();
        cust_id = in.readString();
        status = in.readString();
    }

    public static final Creator<AssignedTasksModel> CREATOR = new Creator<AssignedTasksModel>() {
        @Override
        public AssignedTasksModel createFromParcel(Parcel in) {
            return new AssignedTasksModel(in);
        }

        @Override
        public AssignedTasksModel[] newArray(int size) {
            return new AssignedTasksModel[size];
        }
    };

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getExec_id() {
        return exec_id;
    }

    public void setExec_id(String exec_id) {
        this.exec_id = exec_id;
    }

    public String getmCustAdds() {
        return mCustAdds;
    }

    public void setmCustAdds(String mCustAdds) {
        this.mCustAdds = mCustAdds;
    }

    public String getmCustPhone() {
        return mCustPhone;
    }

    public void setmCustPhone(String mCustPhone) {
        this.mCustPhone = mCustPhone;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getSl_no() {
        return sl_no;
    }

    public void setSl_no(String sl_no) {
        this.sl_no = sl_no;
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

    public String getAdds() {
        return adds;
    }

    public void setAdds(String adds) {
        this.adds = adds;
    }

    public String getAssign_cust_id() {
        return assign_cust_id;
    }

    public void setAssign_cust_id(String assign_cust_id) {
        this.assign_cust_id = assign_cust_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(customerName);
        dest.writeString(exec_id);
        dest.writeString(mCustAdds);
        dest.writeString(mCustPhone);
        dest.writeString(serviceDate);
        dest.writeString(sl_no);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(phone);
        dest.writeString(adds);
        dest.writeString(assign_cust_id);
        dest.writeString(task_id);
        dest.writeString(details);
        dest.writeString(type);
        dest.writeString(cust_id);
        dest.writeString(status);
    }
}
