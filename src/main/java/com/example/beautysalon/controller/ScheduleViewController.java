package com.example.beautysalon.controller;

import com.example.beautysalon.dto.ScheduleItemDTO;
import com.example.beautysalon.entity.Appointment;
import com.example.beautysalon.entity.BeautyService;
import com.example.beautysalon.entity.Master;
import com.example.beautysalon.repository.AppointmentRepository;
import com.example.beautysalon.repository.BeautyServiceRepository;
import com.example.beautysalon.repository.MasterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Controller
public class ScheduleViewController {

    private final AppointmentRepository appointmentRepository;
    private final BeautyServiceRepository beautyServiceRepository;
    private final MasterRepository masterRepository;

    public ScheduleViewController(AppointmentRepository appointmentRepository,
                                  BeautyServiceRepository beautyServiceRepository,
                                  MasterRepository masterRepository) {
        this.appointmentRepository = appointmentRepository;
        this.beautyServiceRepository = beautyServiceRepository;
        this.masterRepository = masterRepository;
    }

    @GetMapping("/schedule-view")
    public String showSchedule(@RequestParam(required = false) String date,
                               Model model) {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate selectedDate;

        if (date == null || date.isBlank()) {
            selectedDate = LocalDate.now();
        } else {
            selectedDate = LocalDate.parse(date);
        }

        int startHour = 8;
        int pixelsPerHour = 90;

        List<ScheduleItemDTO> scheduleItems = appointmentRepository.findAll()
                .stream()
                .filter(appointment ->
                        appointment.getAppointmentDateTime().toLocalDate().equals(selectedDate)
                )
                .sorted(Comparator.comparing(Appointment::getAppointmentDateTime))
                .map(appointment -> {

                    LocalDateTime startDateTime = appointment.getAppointmentDateTime();
                    int duration = appointment.getBeautyService().getDurationMinutes();
                    LocalDateTime endDateTime = startDateTime.plusMinutes(duration);
                    LocalTime startTime = startDateTime.toLocalTime();

                    int minutesFromStart =
                            (startTime.getHour() - startHour) * 60 + startTime.getMinute();

                    int top = minutesFromStart * pixelsPerHour / 60;
                    int height = duration * pixelsPerHour / 60;

                    return new ScheduleItemDTO(
                            appointment.getId(),
                            appointment.getClientName(),
                            appointment.getClientPhone(),
                            appointment.getComment(),
                            appointment.getBeautyService().getName(),
                            appointment.getMaster().getName(),
                            startDateTime.format(timeFormatter),
                            endDateTime.format(timeFormatter),
                            appointment.getBeautyService().getPrice(),
                            top,
                            height
                    );
                })
                .toList();

        model.addAttribute("scheduleItems", scheduleItems);
        model.addAttribute("selectedDate", selectedDate);
        model.addAttribute("services", beautyServiceRepository.findAll());
        model.addAttribute("masters", masterRepository.findAll());

        return "schedule";
    }

    @PostMapping("/schedule-view/add")
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
        appointment.setBeautyService(beautyService);
        appointment.setMaster(master);
        appointment.setComment(comment);

        appointmentRepository.save(appointment);

        LocalDate selectedDate = LocalDateTime.parse(appointmentDateTime).toLocalDate();

        return "redirect:/schedule-view?date=" + selectedDate;
    }

    @PostMapping("/schedule-view/delete/{id}")
    public String deleteAppointment(@PathVariable Long id,
                                    @RequestParam(required = false) String date) {

        appointmentRepository.deleteById(id);

        if (date != null && !date.isBlank()) {
            return "redirect:/schedule-view?date=" + date;
        }

        return "redirect:/schedule-view";
    }
}