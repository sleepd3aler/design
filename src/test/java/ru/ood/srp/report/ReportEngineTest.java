package ru.ood.srp.report;

import java.util.Calendar;
import org.junit.jupiter.api.Test;
import ru.ood.srp.formatter.DateTimeParser;
import ru.ood.srp.formatter.ReportDateTimeParser;
import ru.ood.srp.model.Employee;
import ru.ood.srp.store.MemStore;
import ru.ood.srp.store.Store;

import static org.assertj.core.api.Assertions.assertThat;

class ReportEngineTest {
    @Test
    public void whenOldGenerated() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportEngine(store, parser);
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
}