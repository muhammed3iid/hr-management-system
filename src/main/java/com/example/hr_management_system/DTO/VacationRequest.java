package com.example.hr_management_system.DTO;

import com.example.hr_management_system.Model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class VacationRequest {
    private long employeeId;
    private Date startDate;
    private Date endDate;
}
