package com.nicode.ApexHealth.Service;


import com.nicode.ApexHealth.Repository.patientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private patientRepository patientRepository;


}
