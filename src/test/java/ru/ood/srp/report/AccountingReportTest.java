package ru.ood.srp.report;

import java.util.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.report_generation_app.currency.CurrencyConverter;
import ru.report_generation_app.currency.InMemoryCurrencyConverter;
import ru.report_generation_app.exceptions.GenerationException;
import ru.report_generation_app.formatter.DateTimeParser;
import ru.report_generation_app.formatter.ReportDateTimeParser;
import ru.report_generation_app.model.Employee;
import ru.report_generation_app.report.AccountingReport;
import ru.report_generation_app.report.Report;
import ru.report_generation_app.store.MemStore;
import ru.report_generation_app.store.Store;
import ru.report_generation_app.validator.AccountingReportValidator;
import ru.report_generation_app.validator.EmployeeValidator;
import ru.report_generation_app.validator.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static ru.report_generation_app.currency.Currency.RUB;
import static ru.report_generation_app.currency.Currency.USD;

class AccountingReportTest {
    private Store store;
    private Employee mary;
    private Employee artem;
    private Employee alex;
    private Report report;
    private DateTimeParser<Calendar> parser;
    private CurrencyConverter converter;
    private Validator validator;
    private EmployeeValidator empValidator;

    @BeforeEach
    void setUp() {
        mary = new Employee("Mary", Calendar.getInstance(), Calendar.getInstance(), 1700);
        artem = new Employee("Artem", Calendar.getInstance(), Calendar.getInstance(), 1500);
        alex = new Employee("Alex", Calendar.getInstance(), Calendar.getInstance(), 1000);
        store = new MemStore();
        parser = new ReportDateTimeParser();
        converter = new InMemoryCurrencyConverter();
        validator = new AccountingReportValidator();
        empValidator = new EmployeeValidator();
        report = new AccountingReport(store, converter, RUB, USD, parser, validator, empValidator);
    }

    @Test
    void whenGeneratedAccountingExpectedResult() {
        store.add(alex);
        store.add(artem);
        store.add(mary);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(alex.getName()).append(" ")
                .append(parser.parse(alex.getHired())).append(" ")
                .append(parser.parse(alex.getFired())).append(" ")
                .append(converter.convert(RUB, alex.getSalary(), USD))
                .append(System.lineSeparator())
                .append(artem.getName()).append(" ")
                .append(parser.parse(artem.getHired())).append(" ")
                .append(parser.parse(artem.getFired())).append(" ")
                .append(converter.convert(RUB, artem.getSalary(), USD))
                .append(System.lineSeparator())
                .append(mary.getName()).append(" ")
                .append(parser.parse(mary.getHired())).append(" ")
                .append(parser.parse(mary.getFired())).append(" ")
                .append(converter.convert(RUB, mary.getSalary(), USD))
                .append(System.lineSeparator());
        String res = report.generate(e -> true);
        assertThat(res).isEqualTo(expected.toString());
    }

    @Test
    void whenEmployeesDontFoundThenExceptionThrown() {
        store.add(alex);
        store.add(artem);
        store.add(mary);
        assertThatThrownBy(() -> report.generate(employee -> employee.getName().equals("Andrey")))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining("Cannot generate report by current condition");
    }

    @Test
    void whenStorageContainsNullEmployeeThenExceptionThrown() {
        store.add(artem);
        store.add(null);
        assertThatThrownBy(() -> report.generate(e -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining("Cannot generate report: null employee provided.");
    }

    @Test
    void whenEmployeeMissingNameThenExceptionThrown() {
        store.add(artem);
        alex.setName("");
        store.add(alex);
        store.add(mary);
        assertThatThrownBy(() -> report.generate(e -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining("Employee must contain name.");
    }

    @Test
    void whenEmployeeMissingHiredOrFiredInfoThenExceptionThrown() {
        store.add(artem);
        alex.setFired(null);
        store.add(alex);
        store.add(mary);
        assertThatThrownBy(() -> report.generate(e -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining(alex.getName() + " hired or fired info is missing.");
    }

    @Test
    void whenEmployeesSalaryIsNegativeThenExceptionThrown() {
        store.add(artem);
        alex.setSalary(-1);
        store.add(alex);
        assertThatThrownBy(() -> report.generate(e -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining(alex.getName() + " salary cant be negative or zero.");
    }

    @Test
    void whenEmployeesSalaryIsZeroThenExceptionThrown() {
        store.add(artem);
        alex.setSalary(0);
        store.add(alex);
        assertThatThrownBy(() -> report.generate(e -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining(alex.getName() + " salary cant be negative or zero.");
    }
}