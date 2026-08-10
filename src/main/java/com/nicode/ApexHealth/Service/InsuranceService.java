package com.nicode.ApexHealth.Service;

import com.nicode.ApexHealth.Entity.Insurance;
import com.nicode.ApexHealth.Entity.patient;
import com.nicode.ApexHealth.Repository.InsuranceRepository;
import com.nicode.ApexHealth.Repository.patientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final  patientRepository patientRepository;

    @Transactional
    public patient assignPatientToInsurance(Insurance insurance ,Long patientId){
        patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("patient not found"));

        patient.setInsurance(insurance);


        insurance.setPatient(patient);//Bidirectional consistency maintained


        return patient;
    }
}
