package com.cddmm.Test.repository;

import com.cddmm.Test.model.EquipmentId;
import com.cddmm.Test.model.EquipmentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepo  extends JpaRepository<EquipmentMaster, EquipmentId> {
}
