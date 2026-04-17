package ru.report_generation_app.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import ru.report_generation_app.model.Employee;
import ru.report_generation_app.report.Report;

public class ServiceImpl implements Service {
    private final Map<Integer, Report> reportStorage = new LinkedHashMap<>();
    int ids = 1;

    @Override
    public void addReport(Report report) {
        reportStorage.put(ids++, report);
    }

    @Override
    public void showMenu() {
        System.out.println("==Выберите отчёт: ==");
        reportStorage.forEach((key, value) -> System.out.printf("%d. %s\n", key, value.toString()));
        System.out.println("0. Выход");
    }

    @Override
    public String generate(String answer, Predicate<Employee> filter, String dateFormat) {
        return reportStorage.get(Integer.parseInt(answer)).generate(filter, dateFormat);
    }
}
