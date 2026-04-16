package ru.report_generation_app.report;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import ru.report_generation_app.currency.Currency;
import ru.report_generation_app.currency.CurrencyConverter;
import ru.report_generation_app.formatter.DateTimeParser;
import ru.report_generation_app.model.Employee;
import ru.report_generation_app.store.Store;
import ru.report_generation_app.validator.EmployeeValidator;
import ru.report_generation_app.validator.Validator;

public class AccountingReport implements Report {
    private final Store store;
    private final CurrencyConverter converter;
    private final Currency source;
    private final Currency target;
    private final DateTimeParser<Calendar> parser;

    private final Validator validator;
    private final EmployeeValidator empValidator;

    public AccountingReport(
            Store store, CurrencyConverter converter, Currency source, Currency target,
            DateTimeParser<Calendar> parser, Validator validator, EmployeeValidator empValidator
    ) {
        this.store = store;
        this.converter = converter;
        this.source = source;
        this.target = target;
        this.parser = parser;
        this.validator = validator;
        this.empValidator = empValidator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        List<Employee> res = store.findBy(filter);
        empValidator.validateSearchingResult(res);
        for (Employee employee : res) {
            empValidator.validateEmployee(employee);
            text.append(employee.getName()).append(" ")
                    .append(parser.parse(employee.getHired())).append(" ")
                    .append(parser.parse(employee.getFired())).append(" ")
                    .append(converter.convert(source, employee.getSalary(), target))
                    .append(System.lineSeparator());
        }
        validator.validateReport(text.toString());
        return text.toString();

    }

    @Override
    public String toString() {
        return "AccountingReport";
    }
}
