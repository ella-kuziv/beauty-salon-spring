package com.example.beautysalon.dto;

import java.math.BigDecimal;

public class ScheduleItemDTO {

    private Long id;
    private String clientName;
    private String clientPhone;
    private String comment;
    private String serviceName;
    private String masterName;
    private String startTime;
    private String endTime;
    private BigDecimal price;
    private int top;
    private int height;

    public ScheduleItemDTO(Long id,
                           String clientName,
                           String clientPhone,
                           String comment,
                           String serviceName,
                           String masterName,
                           String startTime,
                           String endTime,
                           BigDecimal price,
                           int top,
                           int height) {
        this.id = id;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.comment = comment;
        this.serviceName = serviceName;
        this.masterName = masterName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.top = top;
        this.height = height;
    }

    public Long getId() { return id; }
    public String getClientName() { return clientName; }
    public String getClientPhone() { return clientPhone; }
    public String getComment() { return comment; }
    public String getServiceName() { return serviceName; }
    public String getMasterName() { return masterName; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public BigDecimal getPrice() { return price; }
    public int getTop() { return top; }
    public int getHeight() { return height; }
}