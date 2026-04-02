package ru.ood.srp.report;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import ru.ood.srp.model.Employee;
import ru.ood.srp.store.Store;
import ru.ood.srp.validator.EmployeeValidator;
import ru.ood.srp.validator.Validator;

public class HrReport implements Report {
    private final Store store;
    private final Validator validator;
    private final EmployeeValidator empValidator;

    public HrReport(Store store, Validator validator, EmployeeValidator empValidator) {
        this.store = store;
        this.validator = validator;
        this.empValidator = empValidator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> list = new ArrayList<>(store.findBy(filter));
        empValidator.validateSearchingResult(list);
        list.sort((e1, e2) -> (int) (e2.getSalary() - e1.getSalary()));
        for (Employee employee : list) {
            empValidator.validateEmployee(employee);
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        validator.validateReport(text.toString());
        return text.toString();
    }
}
