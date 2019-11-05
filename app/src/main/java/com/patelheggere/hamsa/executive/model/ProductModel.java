package com.patelheggere.hamsa.executive.model;

import java.util.List;

public class ProductModel {
    private String productName;
    private String productId;
    private List<Models> models;
    private List<Makes> makes;
    private List<EQType> equipmentType;

    public ProductModel() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<Models> getModels() {
        return models;
    }

    public void setModels(List<Models> models) {
        this.models = models;
    }

    public List<Makes> getMakes() {
        return makes;
    }

    public void setMakes(List<Makes> makes) {
        this.makes = makes;
    }

    public List<EQType> getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(List<EQType> equipmentType) {
        this.equipmentType = equipmentType;
    }
}
