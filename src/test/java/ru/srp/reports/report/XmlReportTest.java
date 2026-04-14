package ru.srp.reports.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.srp.reports.exceptions.GenerationException;
import ru.srp.reports.model.Employee;
import ru.srp.reports.model.Employees;
import ru.srp.reports.store.MemStore;
import ru.srp.reports.store.Store;
import ru.srp.reports.validator.EmployeeValidator;
import ru.srp.reports.validator.Validator;
import ru.srp.reports.validator.XmlReportValidator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class XmlReportTest {
    private Store store;
    private Validator validator;
    private EmployeeValidator employeeValidator;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        store = new MemStore();
        validator = new XmlReportValidator();
        employeeValidator = new EmployeeValidator();
        employee1 = new Employee("John Doe",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                5000.0);
        employee2 = new Employee("Jane Smith",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                6000.0);
    }

    @Test
    void whenGenerated() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        store.add(employee1);
        store.add(employee2);
        Report report = new XmlReport(store, validator, employeeValidator, marshaller);
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>John Doe</name>
                        <hired>08:06:2023 17:41</hired>
                        <fired>08:06:2023 17:41</fired>
                        <salary>5000.0</salary>
                    </employee>
                    <employee>
                        <name>Jane Smith</name>
                        <hired>08:06:2023 17:41</hired>
                        <fired>08:06:2023 17:41</fired>
                        <salary>6000.0</salary>
                    </employee>
                </employees>
                """;
        assertThat(report.generate(em -> true)).isEqualTo(expect);
    }

    @Test
    void whenReportWithIncorrectFormatGeneratedThenExceptionThrown() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        store.add(employee1);
        store.add(employee2);
        Report report = new XmlReport(store, validator, employeeValidator, marshaller);
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>John Doe</name>
                        <hired>08:06:2023 17:41</hired>
                        <fired>08:06:2023 17:41</fired>
                        <salary>5000.0</salary>
                    </employee>
                    <employee>
                        <name>Jane Smith</name>
                        <hired>08:06:2023 17:41</hired>
                        <fired>08:06:2023 17:41</fired>
                        <salary>6000.0</salary>
                    </employee>
                </employees>
                """;
        assertThatThrownBy(() -> report.generate(em -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining("Xml report has wrong format. Set pretty printing");
    }
}