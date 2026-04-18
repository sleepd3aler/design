package ru.report_generation_app;

import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import ru.report_generation_app.adapters.JsonCalendarSerializer;
import ru.report_generation_app.configurations.CachedConfig;
import ru.report_generation_app.configurations.Config;
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
        Employee first = new Employee("John Doe",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                5000.0);

        Store store = new MemStore();
        store.add(first);

        String dateFormat = "";

        GsonBuilder gsonBuilder = getGsonBuilderSetUp(dateFormat);
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        try (CachedConfig cachedConfig = getCachedConfig()) {
            Service service = createService(store, gsonBuilder, context);
            Scanner scanner = new Scanner(System.in);
            execute(scanner, service, cachedConfig);
        }

    }

    private static GsonBuilder getGsonBuilderSetUp(String dateFormat) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeHierarchyAdapter(Calendar.class, new JsonCalendarSerializer(dateFormat));
        return gsonBuilder;
    }

    private static CachedConfig getCachedConfig() {
        FileConfig fileConfig = new FileConfig();
        fileConfig.load("reports/app.properties");
        SqlConfig sqlConfig = new SqlConfig(fileConfig);
        return new CachedConfig(sqlConfig);
    }

    private static Service createService(Store store, GsonBuilder gsonBuilder, JAXBContext context) {
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        var dateTimeParser = new ReportDateTimeParser();
        var employeeValidator = new EmployeeValidator();
        var accountingValidator = new AccountingReportValidator();
        var hrValidator = new HrReportValidator();
        var itValidator = new ItReportValidator();
        var jsonValidator = new JsonReportValidator();
        var xmlValidator = new XmlReportValidator();

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
        Report xmlReport = new XmlReport(store, xmlValidator, employeeValidator, context);

        Service service = new ServiceImpl();
        service.addReport(accountReport);
        service.addReport(itReport);
        service.addReport(hrReport);
        service.addReport(jsonReport);
        service.addReport(xmlReport);
        return service;
    }

    private static void execute(Scanner scanner, Service service, Config config) {
        boolean run = true;
        while (run) {
            service.showMenu();
            String answer = scanner.nextLine();
            if (!answer.equals("0")) {
                String actualFormat = config.get("date_format");
                System.out.println(service.generate(answer, employee -> true, actualFormat));
            } else {
                run = false;
                System.out.println("== Программа завершена ==");
            }
        }
    }
}
