package ru.ood.srp.report;

import java.util.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.report_generation_app.exceptions.GenerationException;
import ru.report_generation_app.model.Employee;
import ru.report_generation_app.report.HrReport;
import ru.report_generation_app.report.Report;
import ru.report_generation_app.store.MemStore;
import ru.report_generation_app.store.Store;
import ru.report_generation_app.validator.EmployeeValidator;
import ru.report_generation_app.validator.HrReportValidator;
import ru.report_generation_app.validator.Validator;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HrReportTest {
    private Store store;
    private Employee mary;
    private Employee artem;
    private Employee alex;
    private Report report;
    private Validator validator;
    private EmployeeValidator empValidator;

    @BeforeEach
    void setUp() {
        mary = new Employee("Mary", Calendar.getInstance(), Calendar.getInstance(), 1700);
        artem = new Employee("Artem", Calendar.getInstance(), Calendar.getInstance(), 1500);
        alex = new Employee("Alex", Calendar.getInstance(), Calendar.getInstance(), 1000);
        store = new MemStore();
        validator = new HrReportValidator();
        empValidator = new EmployeeValidator();
        report = new HrReport(store, validator, empValidator);
    }

    @Test
    void whenGeneratedHrReportThenExpectedResult() {
        store.add(alex);
        store.add(artem);
        store.add(mary);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(mary.getName()).append(" ")
                .append(mary.getSalary())
                .append(System.lineSeparator())
                .append(artem.getName()).append(" ")
                .append(artem.getSalary())
                .append(System.lineSeparator())
                .append(alex.getName()).append(" ")
                .append(alex.getSalary())
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