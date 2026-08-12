package com.nicode.ApexHealth;

import com.nicode.ApexHealth.Entity.Appointment;
import com.nicode.ApexHealth.Service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    private AppointmentService appointmentservice;

    @Test
    public void setAppointment() {

        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2026, 11, 17, 14, 0))
                .reason("routine Checkup")
                .build();

        Appointment Newappointment = appointmentservice.createAppointment(appointment,2L,1L);

        System.out.println(Newappointment);

    }
}
