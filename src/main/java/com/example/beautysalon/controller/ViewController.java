package com.example.beautysalon.controller;

import com.example.beautysalon.entity.BeautyService;
import com.example.beautysalon.repository.BeautyServiceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class ViewController {

    private final BeautyServiceRepository beautyServiceRepository;

    public ViewController(BeautyServiceRepository beautyServiceRepository) {
        this.beautyServiceRepository = beautyServiceRepository;
    }

    @GetMapping("/services-view")
    public String showServices(Model model) {
        model.addAttribute("services", beautyServiceRepository.findAll());
        return "services";
    }

    @PostMapping("/services-view/add")
    public String addService(@RequestParam String name,
                             @RequestParam Integer durationMinutes,
                             @RequestParam Double price) {

        BeautyService service = new BeautyService();
        service.setName(name);
        service.setDurationMinutes(durationMinutes);
        service.setPrice(BigDecimal.valueOf(price));

        beautyServiceRepository.save(service);

        return "redirect:/services-view";
    }

    @PostMapping("/services-view/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        beautyServiceRepository.deleteById(id);
        return "redirect:/services-view";
    }
}