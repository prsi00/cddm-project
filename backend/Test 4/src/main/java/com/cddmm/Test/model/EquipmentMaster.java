package com.cddmm.Test.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table
public class EquipmentMaster {
    @EmbeddedId
    private EquipmentId id;

    private String equipmentDesc;
    private String rangeee;
    private String desiredAccuracy;
    private String frequency;

    private LocalDate lastCalib;
    private int intimitationDays;
    private LocalDate dueDate;
    private String status;
    private String remarks;

    public EquipmentMaster(EquipmentId id, String equipmentDesc, String rangeee, String desiredAccuracy, String frequency, LocalDate lastCalib, int intimitationDays, LocalDate dueDate, String status, String remarks) {
        this.id = id;
        this.equipmentDesc = equipmentDesc;
        this.rangeee = rangeee;
        this.desiredAccuracy = desiredAccuracy;
        this.frequency = frequency;
        this.lastCalib = lastCalib;
        this.intimitationDays = intimitationDays;
        this.dueDate = dueDate;
        this.status = status;
        this.remarks = remarks;
    }

    public EquipmentMaster() {
    }

    public EquipmentId getId() {
        return id;
    }

    public void setId(EquipmentId id) {
        this.id = id;
    }

    public String getEquipmentDesc() {
        return equipmentDesc;
    }

    public void setEquipmentDesc(String equipmentDesc) {
        this.equipmentDesc = equipmentDesc;
    }

    public String getRangeee() {
        return rangeee;
    }

    public void setRangeee(String rangeee) {
        this.rangeee = rangeee;
    }

    public String getDesiredAccuracy() {
        return desiredAccuracy;
    }

    public void setDesiredAccuracy(String desiredAccuracy) {
        this.desiredAccuracy = desiredAccuracy;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalDate getLastCalib() {
        return lastCalib;
    }

    public void setLastCalib(LocalDate lastCalib) {
        this.lastCalib = lastCalib;
    }

    public int getIntimitationDays() {
        return intimitationDays;
    }

    public void setIntimitationDays(int intimitationDays) {
        this.intimitationDays = intimitationDays;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
