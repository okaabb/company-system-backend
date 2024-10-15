package org.project.onboarding.controller;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.project.onboarding.AbstractIntegrationTest;
import org.project.onboarding.TestObjectMapper;
import org.project.onboarding.dto.applicant.ApplicantDTO;
import org.project.onboarding.dto.applicant.GetApplicantResponseDTO;
import org.project.onboarding.dto.applicant.UpdateApplicantDTO;
import org.project.onboarding.entity.Applicant;
import org.project.onboarding.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ApplicantControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestObjectMapper testObjectMapper;

    @Autowired
    private ApplicantRepository applicantRepository;

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void getApplicantById_successTest() throws Exception {
        ResultActions resultActions = this.mockMvc
                .perform(get("/applicants/19")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        GetApplicantResponseDTO getApplicantResponseDTO = testObjectMapper
                .toObject(resultActions.andReturn().getResponse().getContentAsString(), GetApplicantResponseDTO.class);
        assertEquals("el-hag", getApplicantResponseDTO.getName());
        assertEquals("elhag@gmail.com", getApplicantResponseDTO.getEmail());
        assertEquals("01234543245", getApplicantResponseDTO.getMobileNumber());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void getApplicantById_doesntExist_failTest() throws Exception {
        this.mockMvc.perform(get("/applicants/20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void getApplicantById_alreadyDeleted_failTest() throws Exception {
        this.mockMvc.perform(get("/applicants/20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void listApplicants_successTest() throws Exception {
        this.mockMvc
                .perform(get("/applicants")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(greaterThan(0))));
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void deleteApplicant_successTest() throws Exception {
        this.mockMvc
                .perform(delete("/applicants/19")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        Optional<Applicant> deletedApplicant = applicantRepository.findById(19L);
        assertTrue(deletedApplicant.isPresent());
        assertEquals(true, deletedApplicant.get().getIsDeleted());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void deleteApplicant_doesntExist_failTest() throws Exception {
        this.mockMvc
                .perform(delete("/applicants/21")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void deleteApplicant_alreadyDeleted_failTest() throws Exception {
        this.mockMvc
                .perform(delete("/applicants/20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addApplicant_successTest() throws Exception {
        ApplicantDTO applicantRequestDTO = new ApplicantDTO();
        applicantRequestDTO.setName("samia");
        applicantRequestDTO.setEmail("hahanaaahmed@gmail.com");
        applicantRequestDTO.setMobileNumber("01009509080");
        System.out.println(testObjectMapper.toJson(applicantRequestDTO));
        this.mockMvc.perform(post("/applicants")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Applicant savedApplicant = applicantRepository.findByEmailAndIsDeletedFalse("hahanaaahmed@gmail.com");
        assertEquals("samia", savedApplicant.getName());
        assertEquals("hahanaaahmed@gmail.com", savedApplicant.getEmail());
        assertEquals("01009509080", savedApplicant.getMobileNumber());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addApplicant_nullEmail_failTest() throws Exception {
        ApplicantDTO applicantRequestDTO = new ApplicantDTO();
        applicantRequestDTO.setName("samia");
        applicantRequestDTO.setMobileNumber("01009509080");

        this.mockMvc.perform(post("/applicants")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addApplicant_nullMobileNumber_failTest() throws Exception {
        ApplicantDTO applicantRequestDTO = new ApplicantDTO();
        applicantRequestDTO.setName("samia");
        applicantRequestDTO.setEmail("samia@gmail.com");

        this.mockMvc.perform(post("/applicants")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addApplicant_nullName_failTest() throws Exception {
        ApplicantDTO applicantRequestDTO = new ApplicantDTO();
        applicantRequestDTO.setEmail("samia@gmail.com");
        applicantRequestDTO.setMobileNumber("01009509080");

        this.mockMvc.perform(post("/applicants")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addApplicant_badFormatEmail_failTest() throws Exception {
        ApplicantDTO applicantRequestDTO = new ApplicantDTO();
        applicantRequestDTO.setName("samia");
        applicantRequestDTO.setEmail("samiagmail.com");
        applicantRequestDTO.setMobileNumber("01009509080");

        this.mockMvc.perform(post("/applicants")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addApplicant_badFormatMobileNumber_failTest() throws Exception {
        ApplicantDTO applicantRequestDTO = new ApplicantDTO();
        applicantRequestDTO.setName("samia");
        applicantRequestDTO.setEmail("samia@gmail.com");
        applicantRequestDTO.setMobileNumber("0100950908w0");

        this.mockMvc.perform(post("/applicants")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void addApplicant_duplicateEmailOnExistedApplicants_failTest() throws Exception {
        ApplicantDTO applicantRequestDTO = new ApplicantDTO();
        applicantRequestDTO.setName("samia");
        applicantRequestDTO.setEmail("elhag@gmail.com");
        applicantRequestDTO.setMobileNumber("01009509080");

        this.mockMvc.perform(post("/applicants")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void addApplicant_duplicateEmailOnDeletedApplicants_successTest() throws Exception {
        ApplicantDTO applicantRequestDTO = new ApplicantDTO();
        applicantRequestDTO.setName("samia");
        applicantRequestDTO.setEmail("elset@gmail.com");
        applicantRequestDTO.setMobileNumber("01009509080");

        this.mockMvc.perform(post("/applicants")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void addApplicant_duplicateMobileNumberOnExistedApplicants_failTest() throws Exception {
        ApplicantDTO applicantRequestDTO = new ApplicantDTO();
        applicantRequestDTO.setName("samia");
        applicantRequestDTO.setEmail("samia@gmail.com");
        applicantRequestDTO.setMobileNumber("01234543245");

        this.mockMvc.perform(post("/applicants")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void addApplicant_duplicateMobileNumberOnDeletedApplicants_successTest() throws Exception {
        ApplicantDTO applicantRequestDTO = new ApplicantDTO();
        applicantRequestDTO.setName("samia");
        applicantRequestDTO.setEmail("samia@gmail.com");
        applicantRequestDTO.setMobileNumber("01009898764");

        this.mockMvc.perform(post("/applicants")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void updateApplicant_allFields_successTest() throws Exception {
        UpdateApplicantDTO applicantRequestDTO = new UpdateApplicantDTO();
        applicantRequestDTO.setName("samia");
        applicantRequestDTO.setEmail("samia@gmail.com");
        applicantRequestDTO.setMobileNumber("01009509080");

        this.mockMvc.perform(put("/applicants/19")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Applicant savedApplicant = applicantRepository.findByEmailAndIsDeletedFalse("samia@gmail.com");
        assertEquals("samia", savedApplicant.getName());
        assertEquals("samia@gmail.com", savedApplicant.getEmail());
        assertEquals("01009509080", savedApplicant.getMobileNumber());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void updateApplicant_onlyName_successTest() throws Exception {
        UpdateApplicantDTO applicantRequestDTO = new UpdateApplicantDTO();
        applicantRequestDTO.setName("samia");

        this.mockMvc.perform(put("/applicants/19")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Applicant savedApplicant = applicantRepository.findByEmailAndIsDeletedFalse("elhag@gmail.com");
        assertEquals("samia", savedApplicant.getName());
        assertEquals("elhag@gmail.com", savedApplicant.getEmail());
        assertEquals("01234543245", savedApplicant.getMobileNumber());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void updateApplicant_badFormatEmail_failTest() throws Exception {
        UpdateApplicantDTO applicantRequestDTO = new UpdateApplicantDTO();
        applicantRequestDTO.setEmail("samiagmail.com");

        this.mockMvc.perform(put("/applicants/19")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void updateApplicant_badFormatMobileNumber_failTest() throws Exception {
        UpdateApplicantDTO applicantRequestDTO = new UpdateApplicantDTO();
        applicantRequestDTO.setMobileNumber("0100950908w0");

        this.mockMvc.perform(put("/applicants/19")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void updateApplicant_duplicateEmailOnExistedApplicants_failTest() throws Exception {
        UpdateApplicantDTO applicantRequestDTO = new UpdateApplicantDTO();
        applicantRequestDTO.setEmail("elebn@gmail.com");

        this.mockMvc.perform(put("/applicants/19")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void updateApplicant_duplicateEmailOnDeletedApplicants_successTest() throws Exception {
        UpdateApplicantDTO applicantRequestDTO = new UpdateApplicantDTO();
        applicantRequestDTO.setEmail("elset@gmail.com");

        this.mockMvc.perform(put("/applicants/19")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void updateApplicant_duplicateMobileNumberOnExistedApplicants_failTest() throws Exception {
        UpdateApplicantDTO applicantRequestDTO = new UpdateApplicantDTO();
        applicantRequestDTO.setMobileNumber("01299989878");

        this.mockMvc.perform(put("/applicants/19")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void updateApplicant_duplicateMobileNumberOnDeletedApplicants_successTest() throws Exception {
        UpdateApplicantDTO applicantRequestDTO = new UpdateApplicantDTO();
        applicantRequestDTO.setMobileNumber("01009898764");

        this.mockMvc.perform(put("/applicants/19")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void updateApplicant_duplicateOnSameApplicantEmail_successTest() throws Exception {
        UpdateApplicantDTO applicantRequestDTO = new UpdateApplicantDTO();
        applicantRequestDTO.setEmail("elhag@gmail.com");

        this.mockMvc.perform(put("/applicants/19")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void updateApplicant_duplicateOnSameApplicantMobileNumber_successTest() throws Exception {
        UpdateApplicantDTO applicantRequestDTO = new UpdateApplicantDTO();
        applicantRequestDTO.setMobileNumber("01234543245");

        this.mockMvc.perform(put("/applicants/19")
                        .content(testObjectMapper.toJson(applicantRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void testApplicantBuilderDefaultValues() {
        Applicant applicant = Applicant.builder()
                .name("hanou2a")
                .email("hanou2a@example.com")
                .mobileNumber("1234567890")
                .build();
        assertEquals(false, applicant.getIsDeleted());

        applicantRepository.save(applicant);

        Applicant savedApplicant = applicantRepository.findByEmailAndIsDeletedFalse(applicant.getEmail());
        assertEquals(false, savedApplicant.getIsDeleted());

    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void searchApplicant_successTest() throws Exception {
        this.mockMvc.perform(get("/applicants")
                .param("name", "el-hag")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(19)))
                .andExpect(jsonPath("$.content[0].name", is("el-hag")))
                .andExpect(jsonPath("$.content[0].email", is("elhag@gmail.com")))
                .andExpect(jsonPath("$.content[0].mobileNumber", is("01234543245")));
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void searchApplicant_deletedApplicant_successTest() throws Exception {
        this.mockMvc.perform(get("/applicants")
                        .param("name", "el-set")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)));
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/applicant/GetApplicantTestData.xml", type = DatabaseOperation.INSERT)
    void searchApplicant_multipleApplicantsNameWithSamePrefix_successTest() throws Exception {
        this.mockMvc.perform(get("/applicants")
                        .param("name", "el-")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

}