package com.example.hr_management_system.Controller;

import com.example.hr_management_system.DTO.VacationRequest;
import com.example.hr_management_system.DTO.VacationResponse;
import com.example.hr_management_system.Service.VacationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vacation")
public class VacationController {

    private VacationService vacationService;

    @PostMapping
    public VacationResponse submitVacation(@RequestBody VacationRequest vacationRequest) {
        return vacationService.submitVacation(vacationRequest);
    }

    @GetMapping("/{employeeId}")
    public List<VacationResponse> getEmployeeVacationHistory(@PathVariable Long employeeId) {
        return vacationService.getEmployeeVacationHistory(employeeId);
    }

    @GetMapping
    public List<VacationResponse> getAllVacations() {
        return vacationService.getAllVacations();
    }

}
