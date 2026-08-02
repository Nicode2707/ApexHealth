package com.nicode.ApexHealth;

import com.nicode.ApexHealth.Dto.BloodGroupCountResponseEntity;
import com.nicode.ApexHealth.Entity.patient;
import com.nicode.ApexHealth.Repository.patientRepository;
import com.nicode.ApexHealth.Service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class patientTest {

    @Autowired
    private patientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testpatientRepository() {
        List<patient> patientList = patientRepository.findAll();
        System.out.println(patientList);
    }





}
