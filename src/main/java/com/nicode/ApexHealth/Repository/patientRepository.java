package com.nicode.ApexHealth.Repository;

import com.nicode.ApexHealth.Dto.BloodGroupCountResponseEntity;
import com.nicode.ApexHealth.Entity.Type.bloodgroup;
import com.nicode.ApexHealth.Entity.patient;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface patientRepository extends JpaRepository<patient,Long> {


}
