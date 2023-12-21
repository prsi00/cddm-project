package com.cddmm.Test.service;

import com.cddmm.Test.model.EquipmentId;
import com.cddmm.Test.model.EquipmentMaster;
import com.cddmm.Test.model.EquipmentUpdateRequest;
import com.cddmm.Test.repository.EquipmentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CddmService implements CddmServe {
    @Autowired
    private EquipmentRepo equipmentRepo;

    @Override
    public EquipmentMaster addEquipment(EquipmentMaster equipment){
        return equipmentRepo.save(equipment);
    }
    @Override
    public List<EquipmentMaster> viewAllEquipment(){
        List<EquipmentMaster> equipmentList = (List<EquipmentMaster>) equipmentRepo.findAll();

        // Sorting the list by dueDate in descending order using Java streams
        List<EquipmentMaster> sortedEquipmentList = equipmentList.stream()
                .sorted(Comparator.comparing(EquipmentMaster::getDueDate)) // Sorting in descending order
                .collect(Collectors.toList());

        return sortedEquipmentList;
    }

    @Override
    public Optional<EquipmentMaster> getEquipmentByID(EquipmentId eid){
        return equipmentRepo.findById(eid);
    }

    @Override
    public EquipmentMaster updateEquipment(EquipmentUpdateRequest updateRequest){
        EquipmentMaster existingEquipment = equipmentRepo.findById(updateRequest.getEquipmentId()).orElseThrow(()->new EntityNotFoundException("Equipment not found with ID: " + updateRequest.getEquipmentId()));
        existingEquipment.setId(updateRequest.getUpdatedEquipment().getId());
        existingEquipment.setStatus(updateRequest.getUpdatedEquipment().getStatus());
        existingEquipment.setDueDate(updateRequest.getUpdatedEquipment().getLastCalib().plusDays(updateRequest.getUpdatedEquipment().getIntimitationDays()));
        existingEquipment.setRemarks(updateRequest.getUpdatedEquipment().getRemarks());
        existingEquipment.setEquipmentDesc(updateRequest.getUpdatedEquipment().getEquipmentDesc());
        existingEquipment.setLastCalib(updateRequest.getUpdatedEquipment().getLastCalib());
        existingEquipment.setIntimitationDays(updateRequest.getUpdatedEquipment().getIntimitationDays());
        existingEquipment.setRangeee(updateRequest.getUpdatedEquipment().getRangeee());
        existingEquipment.setDesiredAccuracy(updateRequest.getUpdatedEquipment().getDesiredAccuracy());
        existingEquipment.setFrequency(updateRequest.getUpdatedEquipment().getFrequency());
        return equipmentRepo.save(existingEquipment);

    }

    @Override
    @Scheduled(cron = "0 0 4 * * ?", zone = "Asia/Kolkata")
    public void checkMail(){
        List<EquipmentMaster> allEquip=equipmentRepo.findAll();
        LocalDate today = LocalDate.now();
        for(EquipmentMaster equip:allEquip){
            long daysBetween = ChronoUnit.DAYS.between(today, equip.getDueDate());
            if(daysBetween<8){
                sendMail(equip.getId());
            }
        }
    }

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendMail(EquipmentId eid){
        String body="Calibration is due for your Equipment no.:"+eid.getEquipmentNo()+" Plant:"+eid.getPlant();
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("priyeshsinghsocials@gmail.com");
        message.setTo("priyeshkrsingh03@gmail.com");
        message.setText(body);
        message.setSubject("Equipment Calibration Due");


        mailSender.send(message);
        System.out.println("Mail Successful");
    }
}
