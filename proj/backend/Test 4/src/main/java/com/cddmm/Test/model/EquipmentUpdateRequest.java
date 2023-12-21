package com.cddmm.Test.model;

public class EquipmentUpdateRequest {
    private EquipmentId equipmentId;
    private EquipmentMaster updatedEquipment;

    public EquipmentUpdateRequest(EquipmentId equipmentId, EquipmentMaster updatedEquipment) {
        this.equipmentId = equipmentId;
        this.updatedEquipment = updatedEquipment;
    }

    public EquipmentUpdateRequest() {
    }

    public EquipmentId getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(EquipmentId equipmentId) {
        this.equipmentId = equipmentId;
    }

    public EquipmentMaster getUpdatedEquipment() {
        return updatedEquipment;
    }

    public void setUpdatedEquipment(EquipmentMaster updatedEquipment) {
        this.updatedEquipment = updatedEquipment;
    }
}
