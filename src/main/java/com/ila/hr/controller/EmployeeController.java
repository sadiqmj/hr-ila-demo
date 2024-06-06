package com.ila.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ila.hr.common.IRes;
import com.ila.hr.dto.EmployeeCreateDto;
import com.ila.hr.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping("/employees")
    public ResponseEntity<Object> create(@Valid @RequestBody EmployeeCreateDto req) {
        var res = service.create(req);
        if (res.getSuccess())
            return ResponseEntity.ok(res.getContent());
        return ResponseEntity.badRequest().body(res);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var res = service.getById(id);
        if (res.getSuccess())
            return ResponseEntity.ok(res.getContent());
        return ResponseEntity.badRequest().body(res);
    }

    @GetMapping("/employees/cpr/{cpr}")
    public ResponseEntity<Object> getByRefNo(@PathVariable String cpr) {
        var res = service.getByCpr(cpr);
        if (res.getSuccess())
            return ResponseEntity.ok(res.getContent());
        return ResponseEntity.badRequest().body(res);
    }

    @GetMapping("/employees")
    public ResponseEntity<Object> getList(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0.0") double fromSalary,
            @RequestParam(defaultValue = "0.0") double toSalary) {
        var res = service.getList(name, fromSalary, toSalary);
        if (res.getSuccess())
            return ResponseEntity.ok(res.getContent());
        return ResponseEntity.badRequest().body(res);
    }
}
