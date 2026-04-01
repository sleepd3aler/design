package ru.ood.srp.validator;

import java.util.List;
import ru.ood.srp.model.Employee;

public interface Validator {

    void validateEmployee(Employee employee);

    void validateSearchingResult(List<Employee> employeeList);
}
