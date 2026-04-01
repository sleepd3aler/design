package ru.ood.srp.report;

import java.util.Calendar;
import org.junit.jupiter.api.Test;
import ru.ood.srp.currency.CurrencyConverter;
import ru.ood.srp.currency.InMemoryCurrencyConverter;
import ru.ood.srp.formatter.DateTimeParser;
import ru.ood.srp.formatter.ReportDateTimeParser;
import ru.ood.srp.model.Employee;
import ru.ood.srp.store.MemStore;
import ru.ood.srp.store.Store;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.ood.srp.currency.Currency.RUB;
import static ru.ood.srp.currency.Currency.USD;

class AccountingReportTest {

    @Test
    void whenGeneratedAccountingExpectedResult() {
        Employee alex = new Employee("Alex", Calendar.getInstance(), Calendar.getInstance(), 1000);
        Employee artem = new Employee("Artem", Calendar.getInstance(), Calendar.getInstance(), 1500);
        Employee mary = new Employee("Mary", Calendar.getInstance(), Calendar.getInstance(), 1700);
        Store store = new MemStore();
        DateTimeParser<Calendar> dTp = new ReportDateTimeParser();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        store.add(alex);
        store.add(artem);
        store.add(mary);
        Report report = new AccountingReport(store, converter, RUB, USD, dTp);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(alex.getName()).append(" ")
                .append(dTp.parse(alex.getHired())).append(" ")
                .append(dTp.parse(alex.getFired())).append(" ")
                .append(converter.convert(RUB, alex.getSalary(), USD))
                .append(System.lineSeparator())
                .append(artem.getName()).append(" ")
                .append(dTp.parse(artem.getHired())).append(" ")
                .append(dTp.parse(artem.getFired())).append(" ")
                .append(converter.convert(RUB, artem.getSalary(), USD))
                .append(System.lineSeparator())
                .append(mary.getName()).append(" ")
                .append(dTp.parse(mary.getHired())).append(" ")
                .append(dTp.parse(mary.getFired())).append(" ")
                .append(converter.convert(RUB, mary.getSalary(), USD))
                .append(System.lineSeparator());
        String res = report.generate(e -> true);
        assertThat(res).isEqualTo(expected.toString());
    }

}