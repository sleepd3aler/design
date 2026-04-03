package ru.srp.reports.store;

import java.util.List;
import java.util.function.Predicate;
import ru.srp.reports.model.Employee;

public interface Store {
    void add(Employee employee);

    List<Employee> findBy(Predicate<Employee> filter);
}
