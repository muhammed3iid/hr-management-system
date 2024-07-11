package com.example.hr_management_system.Service;

import com.example.hr_management_system.DTO.VacationRequest;
import com.example.hr_management_system.DTO.VacationResponse;
import com.example.hr_management_system.Model.Employee;
import com.example.hr_management_system.Model.Vacation;
import com.example.hr_management_system.Repository.EmployeeRepository;
import com.example.hr_management_system.Repository.VacationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VacationServiceTest {

    @Mock
    private VacationRepository vacationRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private VacationService vacationService;

    private VacationRequest vacationRequest;
    private Vacation vacation;
    private Employee employee;

    @BeforeEach
    void setUp() {
        vacationRequest = VacationRequest.builder()
                .employeeId(1L)
                .startDate(new Date())
                .endDate(new Date())
                .build();

        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");

        vacation = Vacation.builder()
                .id(1L)
                .startDate(new Date())
                .endDate(new Date())
                .employee(employee)
                .build();
    }

    @Test
    void testSubmitVacation() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(vacationRepository.save(any(Vacation.class))).thenReturn(vacation);

        VacationResponse response = vacationService.submitVacation(vacationRequest);

        assertNotNull(response);
        assertEquals(1L, response.getEmployeeId());
        verify(vacationRepository, times(1)).save(any(Vacation.class));
    }

    @Test
    void testGetEmployeeVacationHistory() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(vacationRepository.findAllByEmployeeId(1L)).thenReturn(Collections.singletonList(vacation));

        List<VacationResponse> responses = vacationService.getEmployeeVacationHistory(1L);

        assertNotNull(responses);
        assertEquals(1, responses.size());
        verify(vacationRepository, times(1)).findAllByEmployeeId(1L);
    }

    @Test
    void testGetAllVacations() {
        List<Vacation> vacationList = Collections.singletonList(vacation);
        when(vacationRepository.findAll()).thenReturn(vacationList);

        List<VacationResponse> responses = vacationService.getAllVacations();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        verify(vacationRepository, times(1)).findAll();
    }

}
