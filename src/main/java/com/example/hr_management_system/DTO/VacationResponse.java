package com.example.hr_management_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class VacationResponse {
    private Long id;
    private Long employeeId;
    private Date startDate;
    private Date endDate;
}
