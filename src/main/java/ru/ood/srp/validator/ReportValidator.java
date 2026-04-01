package ru.ood.srp.validator;

import java.util.List;
import ru.ood.srp.exceptions.GererationException;
import ru.ood.srp.model.Employee;

public class ReportValidator implements Validator {
    @Override
    public void validateSearchingResult(List<Employee> employeeList) {
        if (employeeList.isEmpty()) {
            throw new GererationException("Cannot generate report by current condition");
        }
        if (employeeList.contains(null)) {
            throw new GererationException("Cannot generate report: null employee provided.");
        }
    }

    @Override
    public void validateEmployee(Employee employee) {
        if (employee.getName().isBlank()) {
            throw new GererationException("Employee must contain name.");
        }
        if (employee.getHired() == null || employee.getFired() == null) {
            throw new GererationException(employee.getName() + " hired or fired info is missing.");
        }
        if (employee.getSalary() <= 0) {
            throw new GererationException(employee.getName() + " salary cant be negative or zero.");
        }
    }
}
