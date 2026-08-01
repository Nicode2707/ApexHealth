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

//import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface patientRepository extends JpaRepository<patient,Long> {

    patient findByName(String name);

    List<patient> findByBirthDateOrEmail(LocalDate birthDate, String email);

    List<patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);

    List<patient> findByNameContainingOrderByIdDesc(String query);

    @Query("SELECT p FROM patient p where p.bloodGroup = ?1")
    List<patient> findByBloodGroup(@Param("bloodGroup") bloodgroup bloodGroup);

    @Query("select p from patient p where p.birthDate > :birthDate")
    List<patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("select new com.nicode.ApexHealth.Dto.BloodGroupCountResponseEntity(p.bloodgroup," +
            " count(p)) from patient p group by p.bloodgroup")
//    List<Object[]> countEachBloodGroupType();
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

  //  @Query(value = "select * from patient", nativeQuery = true)
  //  Page<patient> findAllPatients(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);


    //    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments")
    List<patient> findAllPatientWithAppointment();

}
