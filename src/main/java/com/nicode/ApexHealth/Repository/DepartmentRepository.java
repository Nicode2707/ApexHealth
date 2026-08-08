package com.nicode.ApexHealth.Repository;

import com.nicode.ApexHealth.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}