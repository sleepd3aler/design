package ru.report_generation_app.report;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;
import ru.report_generation_app.validator.EmployeeValidator;
import ru.report_generation_app.validator.Validator;
import ru.report_generation_app.model.Employee;
import ru.report_generation_app.model.Employees;
import ru.report_generation_app.store.Store;

public class XmlReport implements Report {
    private final Store store;
    private final Validator validator;
    private final EmployeeValidator employeeValidator;
    private final Marshaller marshaller;

    public XmlReport(Store store, Validator validator, EmployeeValidator employeeValidator, Marshaller marshaller) {
        this.store = store;
        this.validator = validator;
        this.employeeValidator = employeeValidator;
        this.marshaller = marshaller;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> res = store.findBy(filter);
        employeeValidator.validateSearchingResult(res);
        res.forEach(employeeValidator::validateEmployee);
        String report = writeToXml(res);
        validator.validateReport(report);
        return report;
    }

    private String writeToXml(List<Employee> employeeList) {
        String xml;
        Employees employees = new Employees(employeeList);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employees, writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e.getMessage());
        }
        return xml;
    }

    @Override
    public String toString() {
        return "XmlReport";
    }
}