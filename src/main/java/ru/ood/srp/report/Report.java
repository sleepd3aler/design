package ru.ood.srp.report;

import java.util.function.Predicate;
import ru.ood.srp.model.Employee;

public interface Report {
    String generate(Predicate<Employee> filter);
}
