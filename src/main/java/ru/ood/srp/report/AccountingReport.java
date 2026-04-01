package ru.ood.srp.report;

import java.util.Calendar;
import java.util.function.Predicate;
import ru.ood.srp.currency.Currency;
import ru.ood.srp.currency.CurrencyConverter;
import ru.ood.srp.formatter.DateTimeParser;
import ru.ood.srp.model.Employee;
import ru.ood.srp.store.Store;

public class AccountingReport implements Report {
    private final Store store;
    private final CurrencyConverter converter;
    private final Currency source;
    private final Currency target;
    private final DateTimeParser<Calendar> parser;

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
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(parser.parse(employee.getHired())).append(" ")
                    .append(parser.parse(employee.getFired())).append(" ")
                    .append(converter.convert(source, employee.getSalary(), target))
                    .append(System.lineSeparator());
        }
        return text.toString();

    }

}
