package com.example.beautysalon.controller;

import com.example.beautysalon.entity.Appointment;
import com.example.beautysalon.entity.BeautyService;
import com.example.beautysalon.entity.Master;
import com.example.beautysalon.repository.AppointmentRepository;
import com.example.beautysalon.repository.BeautyServiceRepository;
import com.example.beautysalon.repository.MasterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class AppointmentViewController {

    private final AppointmentRepository appointmentRepository;
    private final BeautyServiceRepository beautyServiceRepository;
    private final MasterRepository masterRepository;

    public AppointmentViewController(AppointmentRepository appointmentRepository,
                                     BeautyServiceRepository beautyServiceRepository,
                                     MasterRepository masterRepository) {
        this.appointmentRepository = appointmentRepository;
        this.beautyServiceRepository = beautyServiceRepository;
        this.masterRepository = masterRepository;
    }

    @GetMapping("/appointments-view")
    public String showAppointments(Model model) {
        model.addAttribute("appointments", appointmentRepository.findAll());
        model.addAttribute("services", beautyServiceRepository.findAll());
        model.addAttribute("masters", masterRepository.findAll());
        return "appointments";
    }

    @PostMapping("/appointments-view/add")
    public String addAppointment(@RequestParam String clientName,
                                 @RequestParam String clientPhone,
                                 @RequestParam String appointmentDateTime,
                                 @RequestParam Long beautyServiceId,
                                 @RequestParam Long masterId,
                                 @RequestParam(required = false) String comment) {

        BeautyService beautyService = beautyServiceRepository.findById(beautyServiceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new RuntimeException("Master not found"));

        Appointment appointment = new Appointment();
        appointment.setClientName(clientName);
        appointment.setClientPhone(clientPhone);
        appointment.setAppointmentDateTime(LocalDateTime.parse(appointmentDateTime));
        appointment.setComment(comment);
        appointment.setBeautyService(beautyService);
        appointment.setMaster(master);

        appointmentRepository.save(appointment);

        return "redirect:/appointments-view";
    }

    @PostMapping("/appointments-view/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointments-view";
    }
}