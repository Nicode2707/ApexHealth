package com.nicode.ApexHealth;

import com.nicode.ApexHealth.Entity.patient;
import com.nicode.ApexHealth.Repository.patientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class patientTest {

    @Autowired
    private patientRepository patientRepository;

    @Test
    public void testpatientRepository() {
        List<patient> patientList = patientRepository.findAll();
        System.out.println(patientList);

    }

}
