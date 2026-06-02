package com.example.beautysalon.controller;

import com.example.beautysalon.entity.Master;
import com.example.beautysalon.service.MasterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/masters")
public class MasterController {

    private final MasterService masterService;

    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    @GetMapping
    public List<Master> getAllMasters() {
        return masterService.getAllMasters();
    }

    @PostMapping
    public Master createMaster(@RequestBody Master master) {
        return masterService.saveMaster(master);
    }

    @PutMapping("/{id}")
    public Master updateMaster(@PathVariable Long id, @RequestBody Master master) {
        return masterService.updateMaster(id, master);
    }

    @DeleteMapping("/{id}")
    public void deleteMaster(@PathVariable Long id) {
        masterService.deleteMaster(id);
    }
}