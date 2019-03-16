package com.javadeveloperzone;

import java.util.Objects;

public class Employee {
    int empId;
    String empName;
    String empAddress;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Employee(int empId, String empName, String empAddress) {
        this.empId = empId;
        this.empName = empName;
        this.empAddress = empAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return empId == employee.empId &&
                empName.equals(employee.empName) &&
                empAddress.equals(employee.empAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, empName, empAddress);
    }
}
