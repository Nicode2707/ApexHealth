package com.nicode.ApexHealth.Service;

import com.nicode.ApexHealth.Entity.Appointment;
import com.nicode.ApexHealth.Entity.Doctor;
import com.nicode.ApexHealth.Entity.patient;
import com.nicode.ApexHealth.Repository.AppointmentRepository;
import com.nicode.ApexHealth.Repository.DoctorRepository;
import com.nicode.ApexHealth.Repository.patientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final patientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createAppointment(Appointment appointment , Long doctorId, Long patientId) {

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(null);
        patient patient = patientRepository.findById(patientId).orElseThrow(null);

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment already exists");

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        return appointmentRepository.save(appointment);


    }

}
