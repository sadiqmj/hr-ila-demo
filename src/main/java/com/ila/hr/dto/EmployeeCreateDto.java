package com.ila.hr.dto;

import java.util.Date;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeCreateDto {
    @NotBlank(message = "cpr is required")
    @Pattern(regexp = "(^$|[0-9]{9})", message = "cpr must be 9 digits")
    public String cpr;
    @NotBlank(message = "first name is required")
    @Size(min = 1, max = 30, message = "first name should be between 1 and 30 characters")
    public String firstName;
    @NotBlank(message = "last name is required")
    @Size(min = 1, max = 30, message = "last name should be between 1 and 30 characters")
    public String lastName;
    @Past(message = "date of birth must be less than current date")
    public Date dateOfBirth;
    public Date joinDate;
    @DecimalMin(value = "0.0", message = "salaray must be greater than 0")
    @DecimalMax(value = "20000.0", message = "salaray must be greater than 20,000")
    public double salary;
    @NotBlank(message = "department is required")
    @Size(min = 1, max = 50, message = "last name should be between 1 and 50 characters")
    public String department;
    @NotBlank(message = "mobile is required")
    @Pattern(regexp = "(^$|[0-9]{8})", message = "mobile number should have 8 digits")
    public String mobile;
    @NotBlank(message = "email is required")
    @Email(message = "email is not valid")
    public String email;

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
