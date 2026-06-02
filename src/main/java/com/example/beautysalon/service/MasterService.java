package com.example.beautysalon.service;

import com.example.beautysalon.entity.Master;
import com.example.beautysalon.repository.MasterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterService {

    private final MasterRepository masterRepository;

    public MasterService(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    public List<Master> getAllMasters() {
        return masterRepository.findAll();
    }

    public Master saveMaster(Master master) {
        return masterRepository.save(master);
    }

    public Master updateMaster(Long id, Master updatedMaster) {
        Master master = masterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Master not found"));

        master.setName(updatedMaster.getName());
        master.setSpecialization(updatedMaster.getSpecialization());
        master.setExperience(updatedMaster.getExperience());

        return masterRepository.save(master);
    }

    public void deleteMaster(Long id) {
        masterRepository.deleteById(id);
    }
}