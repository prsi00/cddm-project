package com.cddmm.Test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EquipmentId {
    @Column(length = 50)
    private String plant;
    @Column(length = 50)
    private String section;
    @Column(length = 50)
    private String locationOfUse;
    @Column(length = 50)
    private String equipmentNo;
    @Column(length = 50)
    private String equipmentType;

    public EquipmentId(String plant, String section, String locationOfUse, String equipmentNo, String equipmentType) {
        this.plant = plant;
        this.section = section;
        this.locationOfUse = locationOfUse;
        this.equipmentNo = equipmentNo;
        this.equipmentType = equipmentType;
    }

    public EquipmentId() {
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLocationOfUse() {
        return locationOfUse;
    }

    public void setLocationOfUse(String locationOfUse) {
        this.locationOfUse = locationOfUse;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }
}
