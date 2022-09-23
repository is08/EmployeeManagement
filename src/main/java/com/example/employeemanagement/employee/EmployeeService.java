package com.example.employeemanagement.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<EmployeeEntity> allEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity getOne(Integer id) {
        return employeeRepository.findById(id).get();
    }

    public void add(EmployeeEntity employeeEntity) {
        employeeRepository.save(employeeEntity);
    }

    public void update(EmployeeEntity employee) {
        EmployeeEntity emp = employeeRepository.findById(employee.getId()).get();
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setSalary(employee.getSalary());

        employeeRepository.save(emp);
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }
}
