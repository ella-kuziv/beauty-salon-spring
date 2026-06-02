package com.example.beautysalon.external;

import com.example.beautysalon.entity.Appointment;
import com.example.beautysalon.entity.BeautyService;
import com.example.beautysalon.entity.Master;
import com.example.beautysalon.repository.AppointmentRepository;
import com.example.beautysalon.repository.BeautyServiceRepository;
import com.example.beautysalon.repository.MasterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ExternalUserService {

    private final AppointmentRepository appointmentRepository;
    private final MasterRepository masterRepository;
    private final BeautyServiceRepository beautyServiceRepository;

    public ExternalUserService(AppointmentRepository appointmentRepository,
                               MasterRepository masterRepository,
                               BeautyServiceRepository beautyServiceRepository) {
        this.appointmentRepository = appointmentRepository;
        this.masterRepository = masterRepository;
        this.beautyServiceRepository = beautyServiceRepository;
    }

    public Map getRandomUser() {
        String url = "https://jsonplaceholder.typicode.com/users/1";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Map.class);
    }

    public Appointment saveAppointmentFromExternalUser() {
        Map user = getRandomUser();

        String name = (String) user.get("name");
        String phone = ((String) user.get("phone")).split(" ")[0];

        Master master = masterRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Master with id 1 not found"));

        BeautyService beautyService = beautyServiceRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("BeautyService with id 1 not found"));

        Appointment appointment = new Appointment();
        appointment.setClientName(name);
        appointment.setClientPhone(phone);
        appointment.setComment("Created from external API");
        appointment.setAppointmentDateTime(LocalDateTime.now().plusDays(1));
        appointment.setMaster(master);
        appointment.setBeautyService(beautyService);

        return appointmentRepository.save(appointment);
    }
}