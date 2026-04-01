package ru.ood.srp.report;

import java.util.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ood.srp.currency.CurrencyConverter;
import ru.ood.srp.currency.InMemoryCurrencyConverter;
import ru.ood.srp.exceptions.GererationException;
import ru.ood.srp.formatter.DateTimeParser;
import ru.ood.srp.formatter.ReportDateTimeParser;
import ru.ood.srp.model.Employee;
import ru.ood.srp.store.MemStore;
import ru.ood.srp.store.Store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static ru.ood.srp.currency.Currency.RUB;
import static ru.ood.srp.currency.Currency.USD;

class AccountingReportTest {
    private Store store;
    private Employee mary;
    private Employee artem;
    private Employee alex;
    private Report report;
    private DateTimeParser<Calendar> parser;
    private CurrencyConverter converter;

    @BeforeEach
    void setUp() {
        mary = new Employee("Mary", Calendar.getInstance(), Calendar.getInstance(), 1700);
        artem = new Employee("Artem", Calendar.getInstance(), Calendar.getInstance(), 1500);
        alex = new Employee("Alex", Calendar.getInstance(), Calendar.getInstance(), 1000);
        store = new MemStore();
        parser = new ReportDateTimeParser();
        converter = new InMemoryCurrencyConverter();
        report = new AccountingReport(store, converter, RUB, USD, parser);
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
                .isInstanceOf(GererationException.class)
                .hasMessageContaining("Cannot generate report by current condition");
    }

    @Test
    void whenStorageContainsNullEmployeeThenExceptionThrown() {
        store.add(artem);
        store.add(null);
        assertThatThrownBy(() -> report.generate(e -> true))
                .isInstanceOf(GererationException.class)
                .hasMessageContaining("Cannot generate report: null employee provided.");
    }

    @Test
    void whenEmployeeMissingNameThenExceptionThrown() {
        store.add(artem);
        alex.setName("");
        store.add(alex);
        store.add(mary);
        assertThatThrownBy(() -> report.generate(e -> true))
                .isInstanceOf(GererationException.class)
                .hasMessageContaining("Employee must contain name.");
    }

    @Test
    void whenEmployeeMissingHiredOrFiredInfoThenExceptionThrown() {
        store.add(artem);
        alex.setFired(null);
        store.add(alex);
        store.add(mary);
        assertThatThrownBy(() -> report.generate(e -> true))
                .isInstanceOf(GererationException.class)
                .hasMessageContaining(alex.getName() + " hired or fired info is missing.");
    }

    @Test
    void whenEmployeesSalaryIsNegativeThenExceptionThrown() {
        store.add(artem);
        alex.setSalary(-1);
        store.add(alex);
        assertThatThrownBy(() -> report.generate(e -> true))
                .isInstanceOf(GererationException.class)
                .hasMessageContaining(alex.getName() + " salary cant be negative or zero.");
    }

    @Test
    void whenEmployeesSalaryIsZeroThenExceptionThrown() {
        store.add(artem);
        alex.setSalary(0);
        store.add(alex);
        assertThatThrownBy(() -> report.generate(e -> true))
                .isInstanceOf(GererationException.class)
                .hasMessageContaining(alex.getName() + " salary cant be negative or zero.");
    }
}