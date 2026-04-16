package ru.report_generation_app;

import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import ru.report_generation_app.adapters.JsonCalendarSerializer;
import ru.report_generation_app.adapters.XmlCalendarAdapter;
import ru.report_generation_app.configurations.FileConfig;
import ru.report_generation_app.configurations.SqlConfig;
import ru.report_generation_app.currency.Currency;
import ru.report_generation_app.currency.CurrencyConverter;
import ru.report_generation_app.currency.InMemoryCurrencyConverter;
import ru.report_generation_app.formatter.ReportDateTimeParser;
import ru.report_generation_app.model.Employee;
import ru.report_generation_app.model.Employees;
import ru.report_generation_app.report.*;
import ru.report_generation_app.service.Service;
import ru.report_generation_app.service.ServiceImpl;
import ru.report_generation_app.store.MemStore;
import ru.report_generation_app.store.Store;
import ru.report_generation_app.validator.*;

public class Main {
    public static void main(String[] args) throws Exception {
        GsonBuilder gsonBuilder = getGsonBuilder();
        FileConfig fileConfig = getConfig();
        String dateFormat = getDateFormat(fileConfig);
        Marshaller marshaller = getMarshaller(dateFormat);

        Employee first = new Employee("John Doe",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                5000.0);

        Store store = new MemStore();
        store.add(first);

        Service service = createService(dateFormat, store, marshaller, gsonBuilder);
        Scanner scanner = new Scanner(System.in);
        execute(scanner, service);
    }

    private static Marshaller getMarshaller(String dateFormat) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setAdapter(new XmlCalendarAdapter(dateFormat));
        return marshaller;
    }

    private static String getDateFormat(FileConfig fileConfig) throws Exception {
        String dateFormat;
        try (SqlConfig sqlConfig = new SqlConfig(fileConfig)) {
            dateFormat = sqlConfig.get("date_format");
        }
        return dateFormat;
    }

    private static FileConfig getConfig() {
        FileConfig fileConfig = new FileConfig();
        fileConfig.load("reports/app.properties");
        return fileConfig;
    }

    private static GsonBuilder getGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeHierarchyAdapter(Calendar.class, new JsonCalendarSerializer());
        return gsonBuilder;
    }

    private static Service createService(String dateFormat, Store store, Marshaller marshaller, GsonBuilder gsonBuilder) {
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        var dateTimeParser = new ReportDateTimeParser();
        var employeeValidator = new EmployeeValidator();
        var accountingValidator = new AccountingReportValidator();
        var hrValidator = new HrReportValidator();
        var itValidator = new ItReportValidator();
        var jsonValidator = new JsonReportValidator();
        var xmlValidator = new XmlReportValidator(dateFormat);

        Report accountReport = new AccountingReport(
                store,
                converter,
                Currency.USD,
                Currency.RUB,
                dateTimeParser,
                accountingValidator,
                employeeValidator);
        Report itReport = new ItReport(store, dateTimeParser, itValidator, employeeValidator);
        Report hrReport = new HrReport(store, hrValidator, employeeValidator);
        Report jsonReport = new JsonReport(store, jsonValidator, employeeValidator, gsonBuilder);
        Report xmlReport = new XmlReport(store, xmlValidator, employeeValidator, marshaller);

        Service service = new ServiceImpl();
        service.addReport(accountReport);
        service.addReport(itReport);
        service.addReport(hrReport);
        service.addReport(jsonReport);
        service.addReport(xmlReport);
        return service;
    }

    private static void execute(Scanner scanner, Service service) {

        boolean run = true;
        while (run) {
            service.showMenu();
            String answer = scanner.nextLine();
            if (!answer.equals("0")) {
                System.out.println(service.generate(answer, employee -> true));
            } else {
                run = false;
                System.out.println("==Программа завершена. ==");
            }
        }
    }
}
