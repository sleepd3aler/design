package ru.report_generation_app.validator;

import java.util.List;
import ru.report_generation_app.exceptions.GenerationException;
import ru.report_generation_app.model.Employee;

public class EmployeeValidator  {

    public void validateEmployee(Employee employee) {
        if (employee.getName().isBlank()) {
            throw new GenerationException("Employee must contain name.");
        }
        if (employee.getHired() == null || employee.getFired() == null) {
            throw new GenerationException(employee.getName() + " hired or fired info is missing.");
        }
        if (employee.getSalary() <= 0) {
            throw new GenerationException(employee.getName() + " salary cant be negative or zero.");
        }
    }

    public void validateSearchingResult(List<Employee> employeeList) {
        if (employeeList.isEmpty()) {
            throw new GenerationException("Cannot generate report by current condition");
        }
        if (employeeList.contains(null)) {
            throw new GenerationException("Cannot generate report: null employee provided.");
        }
    }

}
