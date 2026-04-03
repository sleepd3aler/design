package ru.srp.reports.store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import ru.srp.reports.model.Employee;

public class MemStore implements Store {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).toList();
    }
}
