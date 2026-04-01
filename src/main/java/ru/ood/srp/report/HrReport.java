package ru.ood.srp.report;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import ru.ood.srp.model.Employee;
import ru.ood.srp.store.Store;
import ru.ood.srp.validator.ReportValidator;
import ru.ood.srp.validator.Validator;

public class HrReport implements Report {
    private final Store store;
    private final Validator validator = new ReportValidator();

    public HrReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> list = new ArrayList<>(store.findBy(filter));
        validator.validateSearchingResult(list);
        list.sort((e1, e2) -> (int) (e2.getSalary() - e1.getSalary()));
        for (Employee employee : list) {
            validator.validateEmployee(employee);
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
