package com.example.beautysalon.service;

import com.example.beautysalon.entity.BeautyService;
import com.example.beautysalon.repository.BeautyServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeautyServiceService {

    private final BeautyServiceRepository beautyServiceRepository;

    public BeautyServiceService(BeautyServiceRepository beautyServiceRepository) {
        this.beautyServiceRepository = beautyServiceRepository;
    }

    public List<BeautyService> getAllServices() {
        return beautyServiceRepository.findAll();
    }

    public BeautyService saveService(BeautyService beautyService) {
        return beautyServiceRepository.save(beautyService);
    }

    public BeautyService updateService(Long id, BeautyService updatedService) {
        BeautyService beautyService = beautyServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        beautyService.setName(updatedService.getName());
        beautyService.setDurationMinutes(updatedService.getDurationMinutes());
        beautyService.setPrice(updatedService.getPrice());

        return beautyServiceRepository.save(beautyService);
    }

    public void deleteService(Long id) {
        beautyServiceRepository.deleteById(id);
    }
}