package com.example.hr_management_system.Repository;

import com.example.hr_management_system.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
