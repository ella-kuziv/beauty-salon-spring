package com.example.beautysalon.controller;

import com.example.beautysalon.entity.Appointment;
import com.example.beautysalon.external.ExternalUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ExternalUserController {

    private final ExternalUserService externalUserService;

    public ExternalUserController(ExternalUserService externalUserService) {
        this.externalUserService = externalUserService;
    }

    @GetMapping("/external-user")
    public Map getExternalUser() {
        return externalUserService.getRandomUser();
    }

    @GetMapping("/external-user/save-appointment")
    public Appointment saveAppointmentFromExternalUser() {
        return externalUserService.saveAppointmentFromExternalUser();
    }
}