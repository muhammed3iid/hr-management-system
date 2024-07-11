package com.example.hr_management_system.Repository;

import com.example.hr_management_system.Model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
    List<Vacation> findAllByEmployeeId(Long employeeId);
}
