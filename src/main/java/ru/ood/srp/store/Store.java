package ru.ood.srp.store;

import java.util.List;
import java.util.function.Predicate;
import ru.ood.srp.model.Employee;

public interface Store {
    void add(Employee employee);

    List<Employee> findBy(Predicate<Employee> filter);
}
