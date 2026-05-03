package ru.isp;

import ru.report_generation_app.model.Employee;

public interface Vehicle {
    void drive();

    int parking();

    void refuel();

    public interface Role {

        void work();

    }

    interface Managing {
        Employee hireEmployee(Employee employee);

        Employee fireEmployee(Employee employee);

        void manageBudget();
    }

    interface Slave {
        void collectCotton();
    }
}
