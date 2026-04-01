package ru.ood.srp.report;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import ru.ood.srp.model.Employee;
import ru.ood.srp.store.Store;

public class HrReport implements Report {
    private final Store store;

    public HrReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> list = new ArrayList<>(store.findBy(filter));
        list.sort((e1, e2) -> (int) (e2.getSalary() - e1.getSalary()));
        for (Employee employee : list) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
