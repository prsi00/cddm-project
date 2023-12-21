package com.cddmm.Test.service;

import com.cddmm.Test.model.EquipmentId;
import com.cddmm.Test.model.EquipmentMaster;
import com.cddmm.Test.model.EquipmentUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface CddmServe {
    EquipmentMaster addEquipment(EquipmentMaster equipment);
    List<EquipmentMaster> viewAllEquipment();

    Optional<EquipmentMaster> getEquipmentByID(EquipmentId eid);

    EquipmentMaster updateEquipment(EquipmentUpdateRequest updateRequest);

    void checkMail();

    void sendMail(EquipmentId eid);

}
