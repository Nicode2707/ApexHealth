package com.nicode.ApexHealth.Repository;

import com.nicode.ApexHealth.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}