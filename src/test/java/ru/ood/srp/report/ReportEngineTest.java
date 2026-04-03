package ru.ood.srp.report;

import java.util.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.srp.reports.exceptions.GenerationException;
import ru.srp.reports.formatter.DateTimeParser;
import ru.srp.reports.formatter.ReportDateTimeParser;
import ru.srp.reports.model.Employee;
import ru.srp.reports.report.ItReport;
import ru.srp.reports.report.Report;
import ru.srp.reports.store.MemStore;
import ru.srp.reports.store.Store;
import ru.srp.reports.validator.EmployeeValidator;
import ru.srp.reports.validator.ItReportValidator;
import ru.srp.reports.validator.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReportEngineTest {
    private Store store;
    private Calendar now;
    private Employee worker;
    private Report engine;
    private DateTimeParser<Calendar> parser;
    private Validator validator;
    private EmployeeValidator empValidator;

    @BeforeEach
    void setUp() {
        store = new MemStore();
        now = Calendar.getInstance();
        worker = new Employee("Ivan", now, now, 100);
        parser = new ReportDateTimeParser();
        validator = new ItReportValidator();
        empValidator = new EmployeeValidator();
        engine = new ItReport(store, parser, validator, empValidator);
    }

    @Test
    public void whenOldGenerated() {
        store.add(worker);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(",")
                .append(parser.parse(worker.getHired())).append(",")
                .append(parser.parse(worker.getFired())).append(",")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    void whenEmployeesDontFoundThenExceptionThrown() {
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(employee -> employee.getName().equals("Alex")))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining("Cannot generate report by current condition");
    }

    @Test
    void whenStorageContainsNullEmployeeThenExceptionThrown() {
        store.add(worker);
        store.add(null);
        assertThatThrownBy(() -> engine.generate(e -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining("Cannot generate report: null employee provided.");
    }

    @Test
    void whenEmployeeMissingNameThenExceptionThrown() {
        worker.setName("");
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(e -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining("Employee must contain name.");
    }

    @Test
    void whenEmployeeMissingHiredOrFiredInfoThenExceptionThrown() {
        worker.setFired(null);
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(e -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining(worker.getName() + " hired or fired info is missing.");
    }

    @Test
    void whenEmployeesSalaryIsNegativeThenExceptionThrown() {
        worker.setSalary(-1);
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(e -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining(worker.getName() + " salary cant be negative or zero.");
    }

    @Test
    void whenEmployeesSalaryIsZeroThenExceptionThrown() {
        worker.setSalary(0);
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(e -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining(worker.getName() + " salary cant be negative or zero.");
    }
}