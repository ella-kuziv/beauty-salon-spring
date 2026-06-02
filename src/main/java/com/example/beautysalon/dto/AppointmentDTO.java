package com.example.beautysalon.dto;

import java.time.LocalDateTime;

public class AppointmentDTO {

    private String clientName;
    private String clientPhone;
    private String comment;
    private LocalDateTime appointmentDateTime;
    private Long masterId;
    private Long beautyServiceId;

    public AppointmentDTO() {
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public Long getMasterId() {
        return masterId;
    }

    public Long getBeautyServiceId() {
        return beautyServiceId;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public void setBeautyServiceId(Long beautyServiceId) {
        this.beautyServiceId = beautyServiceId;
    }
}