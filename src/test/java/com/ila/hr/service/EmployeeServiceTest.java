package com.ila.hr.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

import com.ila.hr.common.EmployeeRes;
import com.ila.hr.common.StatusId;
import com.ila.hr.model.Employee;
import com.ila.hr.repository.EmployeeRep;

// @JsonTest
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService service;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private EmployeeValidator validator;

    @Mock
    private EmployeeRep rep;

    private Employee employee;

    // @Autowired
    // private JacksonTester<EmployeeRes> json;

    @BeforeEach
    public void setUp() {
        employee = getEmployee();
    }

    private Employee getEmployee() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        var emp = new Employee();
        emp.id = 1;
        emp.cpr = "830104300";
        emp.firstName = "ali";
        emp.lastName = "hussain";
        emp.department = "IT";
        emp.mobile = "39088117";
        emp.email = "ali.hussain@gmail.com";
        emp.salary = 1040;
        try {
            emp.dateOfBirth = formatter.parse("1983-01-16");
            emp.joinDate = formatter.parse("2014-10-04");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return emp;
    }

    @Test
    void testGetEmployeebyIdThatDoesNotExist() {
        Mockito.when(rep.getById(employee.id)).thenReturn(null);

        var res = service.getById(employee.id);

        assertThat(res)
                .returns(false, r -> r.getSuccess())
                .returns(StatusId.REC_NOT_EXIST, r -> r.getStatus());
    }

    @Test
    void testGetEmployeebyIdThatExist() throws IOException {
        Mockito.when(rep.getById(employee.id)).thenReturn(employee);

        var res = service.getById(employee.id);

        assertThat(res)
                .returns(true, r -> r.getSuccess())
                .returns(StatusId.SUCCESS, r -> r.getStatus());
        // assertThat(this.json.write(res.getContent())).extractingJsonPathStringValue("@.mobile")
        // .isEqualToIgnoringCase(employee.mobile);
    }
}
