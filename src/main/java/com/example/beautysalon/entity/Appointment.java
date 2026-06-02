package com.example.beautysalon.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String clientPhone;
    private LocalDateTime appointmentDateTime;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "master_id", nullable = false)
    private Master master;

    @ManyToOne
    @JoinColumn(name = "beauty_service_id", nullable = false)
    private BeautyService beautyService;

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public String getComment() {
        return comment;
    }

    public Master getMaster() {
        return master;
    }

    public BeautyService getBeautyService() {
        return beautyService;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public void setBeautyService(BeautyService beautyService) {
        this.beautyService = beautyService;
    }
}