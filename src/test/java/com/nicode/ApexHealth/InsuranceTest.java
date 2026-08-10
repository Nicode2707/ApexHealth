package com.nicode.ApexHealth;

import com.nicode.ApexHealth.Entity.Insurance;
import com.nicode.ApexHealth.Entity.patient;
import com.nicode.ApexHealth.Service.InsuranceService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceservice;

    @Test
    public void insuranceTest() {

        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC12347")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,12,23))
                .build();

        patient patient =  insuranceservice.assignPatientToInsurance(insurance,1L);
        System.out.println(patient);
    }
}
