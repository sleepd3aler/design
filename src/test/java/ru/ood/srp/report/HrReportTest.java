package ru.ood.srp.report;

import java.util.Calendar;
import org.junit.jupiter.api.Test;
import ru.ood.srp.model.Employee;
import ru.ood.srp.store.MemStore;
import ru.ood.srp.store.Store;

import static org.assertj.core.api.Assertions.assertThat;

class HrReportTest {

    @Test
    void whenGeneratedHrReportThenExpectedResult() {
        Employee mary = new Employee("Mary", Calendar.getInstance(), Calendar.getInstance(), 1700);
        Employee artem = new Employee("Artem", Calendar.getInstance(), Calendar.getInstance(), 1500);
        Employee alex = new Employee("Alex", Calendar.getInstance(), Calendar.getInstance(), 1000);
        Store store = new MemStore();
        store.add(alex);
        store.add(artem);
        store.add(mary);
        Report report = new HrReport(store);
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
}