package com.example.dimshw.Services;

import com.example.dimshw.Exceptions.EmployeeNotFoundException;
import com.example.dimshw.Model.Employee;
import com.example.dimshw.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> listAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee findByName1(String name) {
        return employeeRepository.findByName(name);
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee get(Long id) throws EmployeeNotFoundException {
        Optional<Employee> result = employeeRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new EmployeeNotFoundException("Could not find any employee with ID " + id);
    }

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public String findEmployeeNameById(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get().getName();
        } else {
            return "Employee Not Found";
        }
    }

    public void updateEmployee(Long id, Employee employee) throws EmployeeNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setName(employee.getName());
            existingEmployee.setLastname(employee.getLastname());
            existingEmployee.setDepartment(employee.getDepartment());
            employeeRepository.save(existingEmployee);
        } else {
            throw new EmployeeNotFoundException("Could not find any employee with ID " + id);
        }
    }



    public void delete(Long id) throws EmployeeNotFoundException {
        Long count = employeeRepository.countById(id);
        if (count == null || count == 0) {
            throw new EmployeeNotFoundException("Could not find any user with ID " + id);
        }
        employeeRepository.deleteById(id);
    }
}
