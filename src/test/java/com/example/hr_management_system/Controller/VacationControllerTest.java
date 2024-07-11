package com.example.hr_management_system.Controller;

import com.example.hr_management_system.DTO.VacationRequest;
import com.example.hr_management_system.DTO.VacationResponse;
import com.example.hr_management_system.Service.VacationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VacationControllerTest {

    @Mock
    private VacationService vacationService;

    @InjectMocks
    private VacationController vacationController;

    private MockMvc mockMvc;

    private VacationRequest vacationRequest;
    private VacationResponse vacationResponse;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vacationController).build();
        objectMapper = new ObjectMapper();

        vacationRequest = VacationRequest.builder()
                .employeeId(1L)
                .startDate(new Date())
                .endDate(new Date())
                .build();

        vacationResponse = VacationResponse.builder()
                .id(1L)
                .employeeId(1L)
                .startDate(new Date())
                .endDate(new Date())
                .build();
    }

    @Test
    void testSubmitVacation() throws Exception {
        when(vacationService.submitVacation(any(VacationRequest.class))).thenReturn(vacationResponse);

        mockMvc.perform(post("/api/vacation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vacationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(1));

        verify(vacationService, times(1)).submitVacation(any(VacationRequest.class));
    }

    @Test
    void testGetEmployeeVacationHistory() throws Exception {
        when(vacationService.getEmployeeVacationHistory(1L)).thenReturn(Arrays.asList(vacationResponse));

        mockMvc.perform(get("/api/vacation/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeId").value(1));

        verify(vacationService, times(1)).getEmployeeVacationHistory(1L);
    }

    @Test
    void testGetAllVacations() throws Exception {
        when(vacationService.getAllVacations()).thenReturn(Arrays.asList(vacationResponse));

        mockMvc.perform(get("/api/vacation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeId").value(1));

        verify(vacationService, times(1)).getAllVacations();
    }
}
