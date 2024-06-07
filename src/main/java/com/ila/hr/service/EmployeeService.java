package com.ila.hr.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ila.hr.common.CreateRes;
import com.ila.hr.common.EmployeeRes;
import com.ila.hr.common.IAuthenticationFacade;
import com.ila.hr.common.IRes;
import com.ila.hr.common.ResBuilder;
import com.ila.hr.common.StatusId;
import com.ila.hr.dto.EmployeeCreateDto;
import com.ila.hr.model.Employee;
import com.ila.hr.repository.EmployeeRep;

@Service
public class EmployeeService {
    Logger log = LoggerFactory.getLogger(EmployeeValidator.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private EmployeeValidator validator;

    @Autowired
    private EmployeeRep rep;

    public IRes create(EmployeeCreateDto dto) {
        var res = validator.validateCreateEmployee(dto);
        if (!res.getSuccess()) {
            return res;
        }
        var entity = modelMapper.map(dto, Employee.class);
        entity.creatorId = authenticationFacade.getId();
        entity.createDt = Date.from(Instant.now());
        var employee = rep.create(entity);
        // return new ResBuilder().success().data(employee.id).build();
        log.info("new employee created, employee id: {}, created by: {}", employee.id,
                authenticationFacade.getIdentity().getName());
        return new ResBuilder().success().data(new CreateRes(employee.id)).build();
    }

    public IRes getById(int id) {
        var employee = rep.getById(id);
        if (employee == null) {
            log.warn("employee does not exist, employee id: {}", id);
            return new ResBuilder().error(StatusId.REC_NOT_EXIST).build();
        } else {
            var data = modelMapper.map(employee, EmployeeRes.class);
            return new ResBuilder().success().data(data).build();
        }
    }

    public IRes getByCpr(String cpr) {
        var employee = rep.getByCpr(cpr);
        if (employee == null) {
            log.warn("employee does not exist, employee CPR: {}", cpr);
            return new ResBuilder().error(StatusId.REC_NOT_EXIST).build();
        } else {
            var data = modelMapper.map(employee, EmployeeRes.class);
            return new ResBuilder().success().data(data).build();
        }
    }

    public IRes getList(String name, double fromSalary, double toSalary) {
        var employees = rep.getList(name, fromSalary, toSalary);
        var data = modelMapper.map(employees, new ArrayList<EmployeeRes>().getClass());
        return new ResBuilder().success().data(data).build();
    }
}