package ru.ood.srp.report;

import java.util.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.report_generation_app.exceptions.GenerationException;
import ru.report_generation_app.formatter.DateTimeParser;
import ru.report_generation_app.formatter.ReportDateTimeParser;
import ru.report_generation_app.model.Employee;
import ru.report_generation_app.report.ItReport;
import ru.report_generation_app.report.Report;
import ru.report_generation_app.store.MemStore;
import ru.report_generation_app.store.Store;
import ru.report_generation_app.validator.EmployeeValidator;
import ru.report_generation_app.validator.ItReportValidator;
import ru.report_generation_app.validator.Validator;

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
    private String dateFormat;

    @BeforeEach
    void setUp() {
        store = new MemStore();
        now = Calendar.getInstance();
        worker = new Employee("Ivan", now, now, 100);
        parser = new ReportDateTimeParser();
        validator = new ItReportValidator();
        empValidator = new EmployeeValidator();
        engine = new ItReport(store, parser, validator, empValidator);
        dateFormat = "dd:MM:yyyy HH:mm";
    }

    @Test
    public void whenOldGenerated() {
        store.add(worker);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(",")
                .append(parser.parse(worker.getHired(), dateFormat)).append(",")
                .append(parser.parse(worker.getFired(), dateFormat)).append(",")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true, dateFormat)).isEqualTo(expected.toString());
    }

    @Test
    void whenEmployeesDontFoundThenExceptionThrown() {
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(employee -> employee.getName().equals("Alex"), dateFormat))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining("Cannot generate report by current condition");
    }

    @Test
    void whenStorageContainsNullEmployeeThenExceptionThrown() {
        store.add(worker);
        store.add(null);
        assertThatThrownBy(() -> engine.generate(e -> true, dateFormat))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining("Cannot generate report: null employee provided.");
    }

    @Test
    void whenEmployeeMissingNameThenExceptionThrown() {
        worker.setName("");
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(e -> true, dateFormat))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining("Employee must contain name.");
    }

    @Test
    void whenEmployeeMissingHiredOrFiredInfoThenExceptionThrown() {
        worker.setFired(null);
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(e -> true, dateFormat))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining(worker.getName() + " hired or fired info is missing.");
    }

    @Test
    void whenEmployeesSalaryIsNegativeThenExceptionThrown() {
        worker.setSalary(-1);
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(e -> true, dateFormat))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining(worker.getName() + " salary cant be negative or zero.");
    }

    @Test
    void whenEmployeesSalaryIsZeroThenExceptionThrown() {
        worker.setSalary(0);
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(e -> true, dateFormat))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining(worker.getName() + " salary cant be negative or zero.");
    }
}