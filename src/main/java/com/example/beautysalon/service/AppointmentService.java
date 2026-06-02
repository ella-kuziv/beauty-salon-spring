package com.example.beautysalon.service;

import com.example.beautysalon.dto.AppointmentDTO;
import com.example.beautysalon.entity.Appointment;
import com.example.beautysalon.entity.BeautyService;
import com.example.beautysalon.entity.Master;
import com.example.beautysalon.repository.AppointmentRepository;
import com.example.beautysalon.repository.BeautyServiceRepository;
import com.example.beautysalon.repository.MasterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final MasterRepository masterRepository;
    private final BeautyServiceRepository beautyServiceRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              MasterRepository masterRepository,
                              BeautyServiceRepository beautyServiceRepository) {
        this.appointmentRepository = appointmentRepository;
        this.masterRepository = masterRepository;
        this.beautyServiceRepository = beautyServiceRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment saveAppointmentFromDTO(AppointmentDTO dto) {
        Appointment appointment = new Appointment();

        appointment.setClientName(dto.getClientName());
        appointment.setClientPhone(dto.getClientPhone());
        appointment.setComment(dto.getComment());
        appointment.setAppointmentDateTime(dto.getAppointmentDateTime());

        Master master = masterRepository.findById(dto.getMasterId())
                .orElseThrow(() -> new RuntimeException("Master not found"));

        BeautyService beautyService = beautyServiceRepository.findById(dto.getBeautyServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        appointment.setMaster(master);
        appointment.setBeautyService(beautyService);

        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointmentFromDTO(Long id, AppointmentDTO dto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setClientName(dto.getClientName());
        appointment.setClientPhone(dto.getClientPhone());
        appointment.setComment(dto.getComment());
        appointment.setAppointmentDateTime(dto.getAppointmentDateTime());

        Master master = masterRepository.findById(dto.getMasterId())
                .orElseThrow(() -> new RuntimeException("Master not found"));

        BeautyService beautyService = beautyServiceRepository.findById(dto.getBeautyServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        appointment.setMaster(master);
        appointment.setBeautyService(beautyService);

        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}