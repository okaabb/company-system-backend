package org.project.onboarding.controller;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.jupiter.api.Test;
import org.project.onboarding.AbstractIntegrationTest;
import org.project.onboarding.TestObjectMapper;
import org.project.onboarding.dto.application.GetApplicationDTO;
import org.project.onboarding.dto.application.PostApplicationDTO;
import org.project.onboarding.enums.ApplicationStatusEnum;
import org.project.onboarding.enums.ApplyingPositionEnum;
import org.project.onboarding.enums.ReferralTypeEnum;
import org.project.onboarding.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApplicationControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestObjectMapper testObjectMapper;

    @Autowired
    private ApplicationRepository applicationRepository;

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeInApplicationTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/application/GetApplicationTestData.xml", type = DatabaseOperation.INSERT),
    })
    void getApplicationById_successTest() throws Exception {
        ResultActions resultActions = this.mockMvc
                .perform(get("/applications/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        GetApplicationDTO getApplicationDTO = testObjectMapper
                .toObject(resultActions.andReturn().getResponse().getContentAsString(), GetApplicationDTO.class);
        assertEquals("el-hag", getApplicationDTO.getApplicant().getName());
        assertEquals("elhag@gmail.com", getApplicationDTO.getApplicant().getEmail());
        assertEquals("01234543245", getApplicationDTO.getApplicant().getMobileNumber());

        assertEquals("Development Department", getApplicationDTO.getDepartment());

        assertEquals(1L, getApplicationDTO.getHr().getId());
        assertEquals("hanou2a", getApplicationDTO.getHr().getName());
        assertEquals("hanou2a@gmail.com", getApplicationDTO.getHr().getEmail());

        assertEquals(2L, getApplicationDTO.getReferralEmployee().getId());
        assertEquals("mero", getApplicationDTO.getReferralEmployee().getName());
        assertEquals("mero@gmail.com", getApplicationDTO.getReferralEmployee().getEmail());

        assertEquals(ApplicationStatusEnum.PENDING, getApplicationDTO.getApplicationStatus());
        assertEquals(ApplyingPositionEnum.IT, getApplicationDTO.getPosition());
        assertEquals(ReferralTypeEnum.EMPLOYEE, getApplicationDTO.getReferralType());
        assertEquals("www.linkedin.com", getApplicationDTO.getLinkedinProfileUrl());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void getApplicationById_nonExistent_failTest() throws Exception {
        this.mockMvc.perform(get("/applications/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeInApplicationTestData.xml", type = DatabaseOperation.INSERT),
    })
    void saveApplication_successTest() throws Exception {
        PostApplicationDTO postApplicationDTO = new PostApplicationDTO();
        postApplicationDTO.setApplicantId(19L);
        postApplicationDTO.setHrId(1L);
        postApplicationDTO.setDepartmentName("Development Department");
        postApplicationDTO.setReferralEmployeeId(2L);
        postApplicationDTO.setApplicationStatus(ApplicationStatusEnum.PENDING);
        postApplicationDTO.setLinkedinProfileUrl("www.linkedin.com");
        postApplicationDTO.setReferralType(ReferralTypeEnum.EMPLOYEE);
        postApplicationDTO.setPosition(ApplyingPositionEnum.IT);

        this.mockMvc.perform(post("/applications")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}