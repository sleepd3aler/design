package ru.srp;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.util.Calendar;
import java.util.GregorianCalendar;
import ru.srp.reports.adapters.XmlCalendarAdapter;
import ru.srp.reports.configurations.FileConfig;
import ru.srp.reports.configurations.SqlConfig;
import ru.srp.reports.model.Employee;
import ru.srp.reports.model.Employees;
import ru.srp.reports.report.Report;
import ru.srp.reports.report.XmlReport;
import ru.srp.reports.store.MemStore;
import ru.srp.reports.store.Store;
import ru.srp.reports.validator.EmployeeValidator;
import ru.srp.reports.validator.Validator;
import ru.srp.reports.validator.XmlReportValidator;

public class Main {
    public static void main(String[] args) throws JAXBException {
//        var numberGenerator = new SimpleNumberGenerator(new Random(), 10);
//        var generator = new SimpleSequenceGenerator(numberGenerator);
//        var validateGenerator = new ValidateGenerator<>(generator);
//        var loggingGenerator = new LoggingGenerator<>(validateGenerator);
//        SequenceFormatter<Integer> formatter = new SimpleSequenceFormatter(new Formatter());
//        Output output = new SequenceOutput();
//        List<Integer> sequence = loggingGenerator.generate(2);
//        String formatedResult = formatter.format(sequence);
//        output.printMessage(formatedResult);
        FileConfig fileConfig = new FileConfig();
        fileConfig.load("srp/app.properties");
        try (SqlConfig config = new SqlConfig(fileConfig)) {
            String format = config.get("date_format");
            Store store = new MemStore();
            Employee employee1 = new Employee("John Doe",
                    new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                    new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                    5000.0);
            store.add(employee1);
            Validator validator = new XmlReportValidator(format);
            EmployeeValidator empValidator = new EmployeeValidator();
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setAdapter(new XmlCalendarAdapter(format));
            Report report = new XmlReport(store, validator, empValidator, marshaller, config);
            System.out.println(report.generate(employee -> true));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
