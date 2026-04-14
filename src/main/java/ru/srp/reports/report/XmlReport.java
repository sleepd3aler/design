package ru.srp.reports.report;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;
import ru.srp.reports.model.Employee;
import ru.srp.reports.model.Employees;
import ru.srp.reports.store.Store;
import ru.srp.reports.validator.EmployeeValidator;
import ru.srp.reports.validator.Validator;

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
}
