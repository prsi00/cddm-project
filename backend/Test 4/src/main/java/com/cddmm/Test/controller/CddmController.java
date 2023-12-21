package com.cddmm.Test.controller;

import com.cddmm.Test.model.EquipmentId;
import com.cddmm.Test.model.EquipmentMaster;
import com.cddmm.Test.model.EquipmentRequest;
import com.cddmm.Test.model.EquipmentUpdateRequest;
import com.cddmm.Test.repository.EquipmentRepo;
import com.cddmm.Test.service.CddmServe;
import com.cddmm.Test.service.CddmService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CddmController {
    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private CddmServe service;
    @PostMapping("/api/addEquipment")
    public ResponseEntity addEquipment(@RequestBody EquipmentRequest eq){
        EquipmentMaster equipment=new EquipmentMaster();
        equipment.setId(eq.getId());
        equipment.setEquipmentDesc(eq.getEquipmentDesc());
        equipment.setFrequency(eq.getFrequency());
        equipment.setRangeee(eq.getRangeee());
        equipment.setDesiredAccuracy(eq.getDesiredAccuracy());
        equipment.setIntimitationDays(eq.getIntimitationDays());
        equipment.setLastCalib(eq.getLastCalib());
        equipment.setRemarks(eq.getRemarks());
        equipment.setStatus(eq.getStatus());
        equipment.setDueDate(eq.getDueDate(eq.getLastCalib(),eq.getIntimitationDays()));
        return new ResponseEntity<>(service.addEquipment(equipment), HttpStatus.CREATED);
    }
    @GetMapping("/api/viewAll")
    public ResponseEntity<List> viewAllEquipment(){
        return new ResponseEntity<>(service.viewAllEquipment(),HttpStatus.OK);
    }

    @GetMapping("/api/getByID")
    public ResponseEntity<Optional<EquipmentMaster>> getEquipmentByID(@RequestBody EquipmentId eid){
        return new ResponseEntity<>(service.getEquipmentByID(eid),HttpStatus.OK);
    }
    @GetMapping("/api/findById/{plant}/{section}/{locationOfUse}/{equipmentNo}/{equipmentType}")
    public ResponseEntity<Optional<EquipmentMaster>> findById(@PathVariable String plant,
                                                    @PathVariable String section,
                                                    @PathVariable String locationOfUse,
                                                    @PathVariable String equipmentNo,
                                                    @PathVariable String equipmentType) {
        EquipmentId equipmentId = new EquipmentId(plant, section, locationOfUse, equipmentNo, equipmentType);
        Optional<EquipmentMaster> equipment = equipmentRepo.findById(equipmentId);

        if (equipment != null) {
            return new ResponseEntity<>(equipment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/update")
    public ResponseEntity<EquipmentMaster> updateEquipment(@RequestBody EquipmentUpdateRequest updateRequest){

        EquipmentMaster updatedEntity=service.updateEquipment(updateRequest);

        return ResponseEntity.ok(updatedEntity);

    }

    @DeleteMapping("/api/delete/{plant}/{section}/{locationOfUse}/{equipmentNo}/{equipmentType}")
    public Map<String,Boolean> deleteEquipment(@PathVariable String plant, @PathVariable String section, @PathVariable String locationOfUse,
                                               @PathVariable String equipmentNo, @PathVariable String equipmentType){
        EquipmentId equipmentId = new EquipmentId(plant, section, locationOfUse, equipmentNo, equipmentType);
        EquipmentMaster equipment = equipmentRepo.findById(equipmentId).orElseThrow(()->new EntityNotFoundException("Doesnt Exist"));

        equipmentRepo.delete(equipment);

        Map<String,Boolean> resp = new HashMap<>();
        resp.put("deleted",true);
        return resp;

    }





}
