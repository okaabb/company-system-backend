package org.project.onboarding.controller;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.jupiter.api.Test;
import org.project.onboarding.AbstractIntegrationTest;
import org.project.onboarding.TestObjectMapper;
import org.project.onboarding.dto.department.GetDepartmentDTO;
import org.project.onboarding.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DepartmentControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestObjectMapper testObjectMapper;

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeesInDepartmentTestData.xml", type = DatabaseOperation.INSERT),
    })
    void getDepartmentById_successTest() throws Exception {
        ResultActions resultActions = this.mockMvc
                .perform(get("/departments/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        GetDepartmentDTO getDepartmentDTO = testObjectMapper
                .toObject(resultActions.andReturn().getResponse().getContentAsString(), GetDepartmentDTO.class);
        assertEquals("HR Department", getDepartmentDTO.getName());
        assertEquals(2, getDepartmentDTO.getEmployees().size());
    }


    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeesInDepartmentTestData.xml", type = DatabaseOperation.INSERT),
    })
    void getDepartmentById_nonExistent_failTest() throws Exception {
        this.mockMvc.perform(get("/departments/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}