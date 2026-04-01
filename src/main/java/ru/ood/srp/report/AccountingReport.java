package ru.ood.srp.report;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import ru.ood.srp.currency.Currency;
import ru.ood.srp.currency.CurrencyConverter;
import ru.ood.srp.formatter.DateTimeParser;
import ru.ood.srp.model.Employee;
import ru.ood.srp.store.Store;
import ru.ood.srp.validator.ReportValidator;
import ru.ood.srp.validator.Validator;

public class AccountingReport implements Report {
    private final Store store;
    private final CurrencyConverter converter;
    private final Currency source;
    private final Currency target;
    private final DateTimeParser<Calendar> parser;

    private Validator validator = new ReportValidator();

    public AccountingReport(Store store, CurrencyConverter converter, Currency source, Currency target, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.converter = converter;
        this.source = source;
        this.target = target;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        List<Employee> res = store.findBy(filter);
        validator.validateSearchingResult(res);
        for (Employee employee : res) {
            validator.validateEmployee(employee);
            text.append(employee.getName()).append(" ")
                    .append(parser.parse(employee.getHired())).append(" ")
                    .append(parser.parse(employee.getFired())).append(" ")
                    .append(converter.convert(source, employee.getSalary(), target))
                    .append(System.lineSeparator());
        }
        return text.toString();

    }

}
