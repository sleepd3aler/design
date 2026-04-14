package ru.srp.reports.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.function.Predicate;
import ru.srp.reports.model.Employee;
import ru.srp.reports.store.Store;
import ru.srp.reports.validator.EmployeeValidator;
import ru.srp.reports.validator.Validator;

public class JsonReport implements Report {
    private final Store store;
    private final Validator validator;
    private final EmployeeValidator employeeValidator;
    private final Gson gson;

    public JsonReport(Store store, Validator validator, EmployeeValidator employeeValidator, GsonBuilder gson) {
        this.store = store;
        this.validator = validator;
        this.employeeValidator = employeeValidator;
        this.gson = gson.create();

    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> res = store.findBy(filter);
        employeeValidator.validateSearchingResult(res);
        res.forEach(employeeValidator::validateEmployee);
        String json = gson.toJson(res);
        validator.validateReport(json);
        return json;
    }
}
