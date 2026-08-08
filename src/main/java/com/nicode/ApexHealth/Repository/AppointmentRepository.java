package com.nicode.ApexHealth.Repository;

import com.nicode.ApexHealth.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}