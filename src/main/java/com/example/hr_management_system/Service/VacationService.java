package com.example.hr_management_system.Service;

import com.example.hr_management_system.DTO.VacationRequest;
import com.example.hr_management_system.DTO.VacationResponse;
import com.example.hr_management_system.Model.Employee;
import com.example.hr_management_system.Model.Vacation;
import com.example.hr_management_system.Repository.EmployeeRepository;
import com.example.hr_management_system.Repository.VacationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VacationService {

    private VacationRepository vacationRepository;
    private EmployeeRepository employeeRepository;

    public VacationResponse submitVacation(VacationRequest vacationRequest) {
        try {
            Employee employee = employeeRepository.findById(vacationRequest.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            Vacation vacation = Vacation.builder()
                    .startDate(vacationRequest.getStartDate())
                    .endDate(vacationRequest.getEndDate())
                    .employee(employee)
                    .build();

            vacationRepository.save(vacation);
            return mapToDTO(vacation);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while submitting vacation request.", e);
        }
    }

    public List<VacationResponse> getEmployeeVacationHistory(Long employeeId) {
        try {
            employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            List<Vacation> vacations = vacationRepository.findAllByEmployeeId(employeeId);
            if (vacations == null || vacations.isEmpty()) {
                throw new RuntimeException("No vacations found for employee with id: " + employeeId);
            }
            List<VacationResponse> vacationsResponses = new ArrayList<>();
            for (Vacation vacation : vacations) {
                vacationsResponses.add(mapToDTO(vacation));
            }
            return vacationsResponses;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving employee vacation history.", e);
        }
    }

    public List<VacationResponse> getAllVacations() {
        try {
            List<VacationResponse> vacations = new ArrayList<>();
            for (Vacation vacation : vacationRepository.findAll()) {
                vacations.add(mapToDTO(vacation));
            }
            return vacations;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving all vacations.", e);
        }
    }

    public VacationResponse mapToDTO(Vacation vacation) {
        return VacationResponse.builder()
                .id(vacation.getId())
                .employeeId(vacation.getEmployee().getId())
                .startDate(vacation.getStartDate())
                .endDate(vacation.getEndDate())
                .build();
    }
}
