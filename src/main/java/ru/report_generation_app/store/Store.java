package ru.report_generation_app.store;

import java.util.List;
import java.util.function.Predicate;
import ru.report_generation_app.model.Employee;

public interface Store {
    void add(Employee employee);

    List<Employee> findBy(Predicate<Employee> filter);
}
