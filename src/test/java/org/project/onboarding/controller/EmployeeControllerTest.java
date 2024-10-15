package org.project.onboarding.controller;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.jupiter.api.Test;
import org.project.onboarding.AbstractIntegrationTest;
import org.project.onboarding.TestObjectMapper;
import org.project.onboarding.dto.employee.GetEmployeeDTO;
import org.project.onboarding.dto.employee.PostEmployeeDTO;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.enums.PositionEnum;
import org.project.onboarding.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeeControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestObjectMapper testObjectMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    private static PostEmployeeDTO getPostEmployeeDTO() {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setName("hanou2a");
        postEmployeeDTO.setEmail("hanou2a@gmail.com");
        postEmployeeDTO.setMobileNumber("01091974573");
        postEmployeeDTO.setNationalId("30204010101087");
        postEmployeeDTO.setUsername("hanou2a");
        postEmployeeDTO.setHireDate(LocalDate.parse("2024-08-24"));
        postEmployeeDTO.setPosition(PositionEnum.CEO);
        postEmployeeDTO.setPassword("01009509080");
        postEmployeeDTO.setDepartment("Development Department");
        return postEmployeeDTO;
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT)
    void addEmployee_successTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Employee savedEmployee = employeeRepository.findByUsernameAndIsDeletedFalse("hanou2a");
        assertNotNull(savedEmployee);
        assertEquals("hanou2a", savedEmployee.getName());
        assertEquals("hanou2a@gmail.com", savedEmployee.getEmail());
        assertEquals("01091974573", savedEmployee.getMobileNumber());
        assertEquals("30204010101087", savedEmployee.getNationalId());
        assertEquals("hanou2a", savedEmployee.getUsername());
        assertNotNull(savedEmployee.getDepartment());
        assertEquals(1L, savedEmployee.getDepartment().getId());
        assertEquals("2024-08-24", savedEmployee.getHireDate().toString());
        assertEquals("CEO", savedEmployee.getPosition().toString());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_nullEmail_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setEmail(null);

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_nullMobileNumber_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setMobileNumber(null);

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_nullName_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setName(null);

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_nullUsername_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setUsername(null);

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_nullPassword_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setPassword(null);

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_nullHireDate_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setHireDate(null);

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_nullNationalId_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setNationalId(null);

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_nullPosition_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setPosition(null);

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_nullDepartment_successTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setDepartment(null);

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Employee savedEmployee = employeeRepository.findByUsernameAndIsDeletedFalse("hanou2a");
        assertNotNull(savedEmployee);
        assertEquals("hanou2a", savedEmployee.getName());
        assertEquals("hanou2a@gmail.com", savedEmployee.getEmail());
        assertEquals("01091974573", savedEmployee.getMobileNumber());
        assertEquals("30204010101087", savedEmployee.getNationalId());
        assertEquals("hanou2a", savedEmployee.getUsername());
        assertNull(savedEmployee.getDepartment());
        assertEquals("2024-08-24", savedEmployee.getHireDate().toString());
        assertEquals("CEO", savedEmployee.getPosition().toString());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_badFormatEmail_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setEmail("hanou2a.com");

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_badFormatMobileNumber_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setMobileNumber("01998765430");

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_badFormatUsername_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setUsername("user name");

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_badFormatPassword_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setPassword("1234");

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void addEmployee_badFormatNationalId_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setNationalId("12234598797080809");

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
    })
    void addEmployee_nonExistentDepartment_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setDepartment("IT Department");

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentDeletedTestData.xml", type = DatabaseOperation.INSERT),
    })
    void addEmployee_existentButDeletedDepartment_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = getPostEmployeeDTO();
        postEmployeeDTO.setDepartment("My Department");

        this.mockMvc.perform(post("/employees")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_allFields_successTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setName("dodo");
        postEmployeeDTO.setEmail("dodo@gmail.com");
        postEmployeeDTO.setMobileNumber("01091974571");
        postEmployeeDTO.setNationalId("30204010101017");
        postEmployeeDTO.setUsername("dodo");
        postEmployeeDTO.setHireDate(LocalDate.parse("2024-08-24"));
        postEmployeeDTO.setPosition(PositionEnum.IT);
        postEmployeeDTO.setDepartment("Development Department");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(1L);
        assertNotNull(employee);
        assertEquals("dodo@gmail.com", employee.getEmail());
        assertEquals("01091974571", employee.getMobileNumber());
        assertEquals("30204010101017", employee.getNationalId());
        assertEquals("dodo", employee.getName());
        assertEquals("dodo", employee.getUsername());
        assertEquals("2024-08-24", employee.getHireDate().toString());
        assertEquals("IT", employee.getPosition().toString());
        assertEquals("Development Department", employee.getDepartment().getName());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_onlyNameUpdate_successTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setName("mero");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(1L);
        assertNotNull(employee);
        assertEquals("mero", employee.getName());
        assertEquals("hanou2a@gmail.com", employee.getEmail());
        assertEquals("01091974573", employee.getMobileNumber());
        assertEquals("30204010101087", employee.getNationalId());
        assertEquals("hanou2a", employee.getUsername());
        assertEquals("Development Department", employee.getDepartment().getName());
        assertEquals("2024-08-24", employee.getHireDate().toString());
        assertEquals("CEO", employee.getPosition().toString());
        assertEquals("12345", employee.getPassword());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void updateEmployee_badFormatEmail_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setEmail("hanou2a.com");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void updateEmployee_badFormatMobileNumber_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setMobileNumber("01998765430");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void updateEmployee_badFormatUsername_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setUsername("user name");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void updateEmployee_badFormatPassword_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setPassword("1234");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    void updateEmployee_badFormatNationalId_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setNationalId("12234598797080809");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_nonExistentDepartment_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setDepartment("IT Department");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_duplicateEmail_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setEmail("mero@gmail.com");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_duplicateMobileNumber_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setMobileNumber("01011111111");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_duplicateUsername_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setUsername("mero");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_duplicateNationalId_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setNationalId("30204010101089");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeDeletedTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_duplicateEmail_onDeletedEmployees_successTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setEmail("soso@gmail.com");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(1L);
        assertNotNull(employee);
        assertEquals("hanou2a", employee.getName());
        assertEquals("soso@gmail.com", employee.getEmail());
        assertEquals("01091974573", employee.getMobileNumber());
        assertEquals("30204010101087", employee.getNationalId());
        assertEquals("hanou2a", employee.getUsername());
        assertEquals("Development Department", employee.getDepartment().getName());
        assertEquals("2024-08-24", employee.getHireDate().toString());
        assertEquals("CEO", employee.getPosition().toString());
        assertEquals("12345", employee.getPassword());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeDeletedTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_duplicateMobileNumber_onDeletedEmployees_successTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setMobileNumber("01000000000");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(1L);
        assertNotNull(employee);
        assertEquals("hanou2a", employee.getName());
        assertEquals("hanou2a@gmail.com", employee.getEmail());
        assertEquals("01000000000", employee.getMobileNumber());
        assertEquals("30204010101087", employee.getNationalId());
        assertEquals("hanou2a", employee.getUsername());
        assertEquals("Development Department", employee.getDepartment().getName());
        assertEquals("2024-08-24", employee.getHireDate().toString());
        assertEquals("CEO", employee.getPosition().toString());
        assertEquals("12345", employee.getPassword());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeDeletedTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_duplicateUsername_onDeletedEmployees_successTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setUsername("soso");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(1L);
        assertNotNull(employee);
        assertEquals("hanou2a", employee.getName());
        assertEquals("hanou2a@gmail.com", employee.getEmail());
        assertEquals("01091974573", employee.getMobileNumber());
        assertEquals("30204010101087", employee.getNationalId());
        assertEquals("soso", employee.getUsername());
        assertEquals("Development Department", employee.getDepartment().getName());
        assertEquals("2024-08-24", employee.getHireDate().toString());
        assertEquals("CEO", employee.getPosition().toString());
        assertEquals("12345", employee.getPassword());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeDeletedTestData.xml", type = DatabaseOperation.INSERT),

    })
    void updateEmployee_duplicateNationalId_onDeletedEmployees_successTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setNationalId("30204010101088");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(1L);
        assertNotNull(employee);
        assertEquals("hanou2a", employee.getName());
        assertEquals("hanou2a@gmail.com", employee.getEmail());
        assertEquals("01091974573", employee.getMobileNumber());
        assertEquals("30204010101088", employee.getNationalId());
        assertEquals("hanou2a", employee.getUsername());
        assertEquals("Development Department", employee.getDepartment().getName());
        assertEquals("2024-08-24", employee.getHireDate().toString());
        assertEquals("CEO", employee.getPosition().toString());
        assertEquals("12345", employee.getPassword());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_duplicateEmailSameEmployee_successTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setEmail("hanou2a@gmail.com");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_duplicateMobileNumberSameEmployee_successTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setMobileNumber("01091974573");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_duplicateUsernameSameEmployee_successTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setUsername("hanou2a");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_duplicateNationalIdSameEmployee_successTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setNationalId("30204010101087");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentDeletedTestData.xml", type = DatabaseOperation.INSERT),
    })
    void updateEmployee_existentButDeletedDepartment_failTest() throws Exception {
        PostEmployeeDTO postEmployeeDTO = new PostEmployeeDTO();
        postEmployeeDTO.setDepartment("My Department");

        this.mockMvc.perform(put("/employees/1")
                        .content(testObjectMapper.toJson(postEmployeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT)
    })
    void getEmployeeById_successTest() throws Exception {
        ResultActions response = this.mockMvc.perform(
                        get("/employees/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        GetEmployeeDTO employeeDTO = testObjectMapper.toObject(response.andReturn().getResponse().getContentAsString(), GetEmployeeDTO.class);
        assertNotNull(employeeDTO);
        assertEquals("hanou2a", employeeDTO.getName());
        assertEquals("hanou2a@gmail.com", employeeDTO.getEmail());
        assertEquals("01091974573", employeeDTO.getMobileNumber());
        assertEquals("30204010101087", employeeDTO.getNationalId());
        assertEquals("hanou2a", employeeDTO.getUsername());
        assertEquals("Development Department", employeeDTO.getDepartment());
        assertEquals("2024-08-24", employeeDTO.getHireDate().toString());
        assertEquals("CEO", employeeDTO.getPosition().toString());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT)
    })
    void getEmployeeById_nonExistentId_failTest() throws Exception {
        this.mockMvc.perform(get("/employees/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeDeletedTestData.xml", type = DatabaseOperation.INSERT)
    })
    void getEmployeeById_existentButDeletedId_failTest() throws Exception {
        this.mockMvc.perform(get("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void listEmployees_successTest() throws Exception {
        this.mockMvc
                .perform(get("/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(greaterThan(0))));
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void deleteEmployee_successTest() throws Exception {
        this.mockMvc.perform(delete("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        Employee deletedEmployee = employeeRepository.findByIdAndIsDeletedFalse(1L);
        assertNull(deletedEmployee);
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void deleteApplicant_doesntExist_failTest() throws Exception {
        this.mockMvc.perform(delete("/employees/21")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeDeletedTestData.xml", type = DatabaseOperation.INSERT),
    })
    void deleteApplicant_alreadyDeleted_failTest() throws Exception {
        this.mockMvc.perform(delete("/employees/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void searchEmployee_successTest() throws Exception {
        this.mockMvc.perform(get("/employees")
                        .param("name", "hanou2a")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].name", is("hanou2a")))
                .andExpect(jsonPath("$.content[0].email", is("hanou2a@gmail.com")));
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeDeletedTestData.xml", type = DatabaseOperation.INSERT),
    })
    void searchEmployee_deletedEmployee_successTest() throws Exception {
        this.mockMvc.perform(get("/employees")
                        .param("name", "soso")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)));
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    @Transactional
    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbUnit/department/DepartmentTestData.xml", type = DatabaseOperation.INSERT),
            @DatabaseSetup(value = "/dbUnit/employee/EmployeeTestData.xml", type = DatabaseOperation.INSERT),
    })
    void searchEmployee_multipleEmployeesNameWithSamePrefix_successTest() throws Exception {
        this.mockMvc.perform(get("/employees")
                        .param("name", "ha")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

}