package ru.srp.reports.report;

import java.util.function.Predicate;
import ru.srp.reports.model.Employee;

public interface Report {
    String generate(Predicate<Employee> filter);
}
