package ru.report_generation_app.report;

import java.util.function.Predicate;
import ru.report_generation_app.model.Employee;

public interface Report {
    String generate(Predicate<Employee> filter, String dateFormat);

    String toString();
}
