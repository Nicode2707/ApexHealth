package com.nicode.ApexHealth.Repository;

import com.nicode.ApexHealth.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}