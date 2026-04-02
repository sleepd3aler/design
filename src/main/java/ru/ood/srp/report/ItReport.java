package ru.ood.srp.report;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import ru.ood.srp.formatter.DateTimeParser;
import ru.ood.srp.model.Employee;
import ru.ood.srp.store.Store;
import ru.ood.srp.validator.EmployeeValidator;
import ru.ood.srp.validator.Validator;

public class ItReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Validator validator;
    private final EmployeeValidator empValidator;

    public ItReport(Store store, DateTimeParser<Calendar> dateTimeParser, Validator validator, EmployeeValidator empValidator) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.validator = validator;
        this.empValidator = empValidator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        List<Employee> result = store.findBy(filter);
        empValidator.validateSearchingResult(result);
        for (Employee employee : store.findBy(filter)) {
            empValidator.validateEmployee(employee);
            text.append(employee.getName()).append(",")
                    .append(dateTimeParser.parse(employee.getHired())).append(",")
                    .append(dateTimeParser.parse(employee.getFired())).append(",")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        validator.validateReport(text.toString());
        return text.toString();
    }
}
