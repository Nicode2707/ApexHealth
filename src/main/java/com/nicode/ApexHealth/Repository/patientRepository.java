package com.nicode.ApexHealth.Repository;

import com.nicode.ApexHealth.Entity.patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface patientRepository extends JpaRepository<patient,Long> {

}
