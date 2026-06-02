package com.example.beautysalon.controller;

import com.example.beautysalon.entity.BeautyService;
import com.example.beautysalon.service.BeautyServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class BeautyServiceController {

    private final BeautyServiceService beautyServiceService;

    public BeautyServiceController(BeautyServiceService beautyServiceService) {
        this.beautyServiceService = beautyServiceService;
    }

    @GetMapping
    public List<BeautyService> getAllServices() {
        return beautyServiceService.getAllServices();
    }

    @PostMapping
    public BeautyService createService(@RequestBody BeautyService service) {
        return beautyServiceService.saveService(service);
    }

    @PutMapping("/{id}")
    public BeautyService updateService(@PathVariable Long id, @RequestBody BeautyService service) {
        return beautyServiceService.updateService(id, service);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        beautyServiceService.deleteService(id);
    }
}