package com.ila.hr.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ila.hr.common.CustomDbConfig;
import com.ila.hr.model.Employee;
import com.ila.hr.service.EmployeeValidator;

@Service
public class EmployeeRep {
    Logger log = LoggerFactory.getLogger(EmployeeValidator.class);

    // todo generate random unique query
    // private String FILE_NAME() = "/home/user01/lab/ila/hr/hr.db";
    @Autowired
    private CustomDbConfig dbConfig;

    private String getFileLocation() {
        return dbConfig.getLocation();
    }

    private int generateId() {
        return java.lang.Math.abs(new Random().nextInt());// todo get latest integer number and store it in a file
    }

    private File getFile() throws IOException {
        var file = new File(getFileLocation());
        if (file.createNewFile()) {
            log.info("database file created for the first time");
            Files.write(Paths.get(getFileLocation()), "[]".getBytes(), StandardOpenOption.WRITE);
        }
        return file;
    }

    private List<Employee> loadEmployees() {
        try {
            var file = getFile();
            var stream = new FileInputStream(file);
            // todo check if file is empty to avoid exception

            var mapper = new ObjectMapper();
            return mapper.readValue(stream, new TypeReference<List<Employee>>() {
            });
        } catch (IOException e) {
            log.error("could not read database file", e);
            return new ArrayList<>();
        }
    }

    private boolean storeEmployees(List<Employee> employees) {
        try {
            var file = getFile();
            var stream = new FileOutputStream(file);

            var mapper = new ObjectMapper();
            mapper.writeValue(stream, employees);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Employee create(Employee employee) {
        employee.id = generateId();
        var employees = loadEmployees();
        employees.add(employee);
        storeEmployees(employees);
        return employee;
    }

    public Employee getById(int id) {
        var employees = loadEmployees();
        var employee = employees.stream().filter(em -> em.id == id).findFirst();
        return employee.isPresent() ? employee.get() : null;
    }

    public Employee getByCpr(String cpr) {
        var employees = loadEmployees();
        var employee = employees.stream().filter(em -> em.cpr.equals(cpr)).findFirst();
        return employee.isPresent() ? employee.get() : null;
    }

    public List<Employee> getList(String name, double fromSalary, double toSalary) {
        var employees = loadEmployees();

        if (!name.isEmpty() && name != null) {
            employees = employees.stream()
                    .filter(em -> em.firstName.toLowerCase().contains(name.toLowerCase()) || em.lastName.toLowerCase()
                            .contains(name
                                    .toLowerCase()))
                    .toList();
        }

        if (fromSalary > 0) {
            employees = employees.stream().filter(em -> em.salary >= fromSalary).toList();
        }

        if (toSalary > 0) {
            employees = employees.stream().filter(em -> em.salary <= toSalary).toList();
        }

        return employees;
    }

    public boolean employeeEmailExists(String email) {
        var employees = loadEmployees();
        return employees.stream().filter(em -> em.email.toLowerCase().equals(email.toLowerCase())).toList().size() > 0;
    }

    public boolean employeeMobileExists(String mobile) {
        var employees = loadEmployees();
        return employees.stream().filter(em -> em.mobile.equals(mobile)).toList().size() > 0;
    }

    public boolean employeeCprExists(String cpr) {
        var employees = loadEmployees();
        return employees.stream().filter(em -> em.cpr.equals(cpr)).toList().size() > 0;
    }
}
