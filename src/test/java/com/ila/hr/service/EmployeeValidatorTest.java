package com.ila.hr.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.ila.hr.repository.EmployeeRep;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeValidatorTest {
    @InjectMocks
    private EmployeeValidator validator;

    @Mock
    private EmployeeRep rep;

    @Test
    void testCprIsNotDuplicate() {
        var cpr = "881104310";
        Mockito.when(rep.employeeCprExists(cpr)).thenReturn(false);
        assertThat(validator.employeeCprExists(cpr)).isFalse();
    }

    @Test
    void testCprIsDuplicate() {
        var cpr = "881104310";
        Mockito.when(rep.employeeCprExists(cpr)).thenReturn(true);
        assertThat(validator.employeeCprExists(cpr)).isTrue();
    }

    @Test
    void testMobileNoteIsDuplicate() {
        var mobile = "39080004";
        Mockito.when(rep.employeeMobileExists(mobile)).thenReturn(false);
        assertThat(validator.employeeMobileExists(mobile)).isFalse();
    }

    @Test
    void testMobileIsDuplicate() {
        var mobile = "39080004";
        Mockito.when(rep.employeeMobileExists(mobile)).thenReturn(true);
        assertThat(validator.employeeMobileExists(mobile)).isTrue();
    }

    @Test
    void testEmailIsNotDuplicate() {
        var email = "abc@abc.com";
        Mockito.when(rep.employeeEmailExists(email)).thenReturn(false);
        assertThat(validator.employeeEmailExists(email)).isFalse();
    }

    @Test
    void testEmailIsDuplicate() {
        var email = "abc@abc.com";
        Mockito.when(rep.employeeEmailExists(email)).thenReturn(true);
        assertThat(validator.employeeEmailExists(email)).isTrue();
    }
}
