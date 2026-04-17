package ru.report_generation_app.report;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import ru.report_generation_app.formatter.DateTimeParser;
import ru.report_generation_app.model.Employee;
import ru.report_generation_app.store.Store;
import ru.report_generation_app.validator.EmployeeValidator;
import ru.report_generation_app.validator.Validator;

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
    public String generate(Predicate<Employee> filter, String dateFormat) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        List<Employee> result = store.findBy(filter);
        empValidator.validateSearchingResult(result);
        for (Employee employee : store.findBy(filter)) {
            empValidator.validateEmployee(employee);
            text.append(employee.getName()).append(",")
                    .append(dateTimeParser.parse(employee.getHired(), dateFormat)).append(",")
                    .append(dateTimeParser.parse(employee.getFired(), dateFormat)).append(",")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        validator.validateReport(text.toString(), dateFormat);
        return text.toString();
    }

    @Override
    public String toString() {
        return "ItReport";
    }
}
