package ru.ood.srp.report;

import java.util.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ood.srp.exceptions.GererationException;
import ru.ood.srp.formatter.DateTimeParser;
import ru.ood.srp.formatter.ReportDateTimeParser;
import ru.ood.srp.model.Employee;
import ru.ood.srp.store.MemStore;
import ru.ood.srp.store.Store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReportEngineTest {
    private Store store;
    private Calendar now;
    private Employee worker;
    private Report engine;
    private DateTimeParser<Calendar> parser;

    @BeforeEach
    void setUp() {
        store = new MemStore();
        now = Calendar.getInstance();
        worker = new Employee("Ivan", now, now, 100);
        parser = new ReportDateTimeParser();
        engine = new ReportEngine(store, parser);
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
                .isInstanceOf(GererationException.class)
                .hasMessageContaining("Cannot generate report by current condition");
    }

    @Test
    void whenStorageContainsNullEmployeeThenExceptionThrown() {
        store.add(worker);
        store.add(null);
        assertThatThrownBy(() -> engine.generate(e -> true))
                .isInstanceOf(GererationException.class)
                .hasMessageContaining("Cannot generate report: null employee provided.");
    }

    @Test
    void whenEmployeeMissingNameThenExceptionThrown() {
        worker.setName("");
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(e -> true))
                .isInstanceOf(GererationException.class)
                .hasMessageContaining("Employee must contain name.");
    }

    @Test
    void whenEmployeeMissingHiredOrFiredInfoThenExceptionThrown() {
        worker.setFired(null);
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(e -> true))
                .isInstanceOf(GererationException.class)
                .hasMessageContaining(worker.getName() + " hired or fired info is missing.");
    }

    @Test
    void whenEmployeesSalaryIsNegativeThenExceptionThrown() {
        worker.setSalary(-1);
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(e -> true))
                .isInstanceOf(GererationException.class)
                .hasMessageContaining(worker.getName() + " salary cant be negative or zero.");
    }

    @Test
    void whenEmployeesSalaryIsZeroThenExceptionThrown() {
        worker.setSalary(0);
        store.add(worker);
        assertThatThrownBy(() -> engine.generate(e -> true))
                .isInstanceOf(GererationException.class)
                .hasMessageContaining(worker.getName() + " salary cant be negative or zero.");
    }
}