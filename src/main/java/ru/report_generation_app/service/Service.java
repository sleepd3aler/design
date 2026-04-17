package ru.report_generation_app.service;

import java.util.function.Predicate;
import ru.report_generation_app.model.Employee;
import ru.report_generation_app.report.Report;

public interface Service {
    void addReport(Report report);

    void showMenu();

    String generate(String answer, Predicate<Employee> filter, String format);

}
