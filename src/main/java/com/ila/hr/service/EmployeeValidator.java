package com.ila.hr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ila.hr.common.IRes;
import com.ila.hr.common.ResBuilder;
import com.ila.hr.common.StatusId;
import com.ila.hr.dto.EmployeeCreateDto;
import com.ila.hr.repository.EmployeeRep;

@Component
public class EmployeeValidator {
    Logger log = LoggerFactory.getLogger(EmployeeValidator.class);

    @Autowired
    private EmployeeRep rep;

    public IRes validateCreateEmployee(EmployeeCreateDto employee) {
        if (employeeCprExists(employee.cpr)) {
            return new ResBuilder().error(StatusId.EMPLOYEE_CPR_DUPLICATE).message("CPR is duplicate").build();
        }

        if (employeeEmailExists(employee.email)) {
            return new ResBuilder().error(StatusId.EMPLOYEE_EMAIL_DUPLICATE).message("email is duplicate").build();
        }

        if (employeeMobileExists(employee.mobile)) {
            return new ResBuilder().error(StatusId.EMPLOYEE_MOBILE_DUPLICATE).message("mobile is duplicate").build();
        }
        return new ResBuilder().success().build();
    }

    private boolean employeeEmailExists(String email) {
        if (rep.employeeEmailExists(email)) {
            log.warn("employee email {} is duplicate", email);
            return true;
        }
        return false;
    }

    private boolean employeeMobileExists(String mobile) {
        if (rep.employeeMobileExists(mobile)) {
            log.warn("employee mobile {} is duplicate", mobile);
            return true;
        }
        return false;
    }

    private boolean employeeCprExists(String cpr) {
        if (rep.employeeCprExists(cpr)) {
            log.warn("employee CPR {} is duplicate", cpr);
            return true;
        }
        return false;
    }
}
