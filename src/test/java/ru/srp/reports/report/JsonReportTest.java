package ru.srp.reports.report;

import com.google.gson.GsonBuilder;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.report_generation_app.adapters.JsonCalendarSerializer;
import ru.report_generation_app.exceptions.GenerationException;
import ru.report_generation_app.model.Employee;
import ru.report_generation_app.report.JsonReport;
import ru.report_generation_app.report.Report;
import ru.report_generation_app.store.MemStore;
import ru.report_generation_app.store.Store;
import ru.report_generation_app.validator.EmployeeValidator;
import ru.report_generation_app.validator.JsonReportValidator;
import ru.report_generation_app.validator.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JsonReportTest {
    private Store store;
    private Validator validator;
    private EmployeeValidator employeeValidator;
    private GsonBuilder gsonBuilder;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        store = new MemStore();
        validator = new JsonReportValidator();
        employeeValidator = new EmployeeValidator();
        employee1 = new Employee("John Doe",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                5000.0);
        employee2 = new Employee("Jane Smith",
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41),
                6000.0);
        gsonBuilder = new GsonBuilder();

    }

    @Test
    void whenAccountantsGenerated() {
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeHierarchyAdapter(Calendar.class, new JsonCalendarSerializer());
        store.add(employee1);
        store.add(employee2);
        Report engine = new JsonReport(store, validator, employeeValidator, gsonBuilder);
        String ex = """
                [
                  {
                    "name": "John Doe",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 5000.0
                  },
                  {
                    "name": "Jane Smith",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 6000.0
                  }
                ]""";
        assertThat(engine.generate(em -> true)).isEqualTo(ex);
    }

    @Test
    void whenReportHavingIncorrectFormatThenExceptionThrown() {
        store.add(employee1);
        store.add(employee2);
        Report engine = new JsonReport(store, validator, employeeValidator, gsonBuilder);
        String ex = """
                [
                  {
                    "name": "John Doe",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 5000.0
                  },
                  {
                    "name": "Jane Smith",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 6000.0
                  }
                ]""";
        assertThatThrownBy(() -> engine.generate(em -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining("Json report has wrong format. Set pretty printing");
    }

    @Test
    void whenReportHavingIncorrectDateTimeFormatThenExceptionThrown() {
        gsonBuilder.setPrettyPrinting();
        store.add(employee1);
        store.add(employee2);
        Report engine = new JsonReport(store, validator, employeeValidator, gsonBuilder);
        String ex = """
                [
                  {
                    "name": "John Doe",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 5000.0
                  },
                  {
                    "name": "Jane Smith",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 6000.0
                  }
                ]""";
        assertThatThrownBy(() -> engine.generate(em -> true))
                .isInstanceOf(GenerationException.class)
                .hasMessageContaining("Illegal Date : Time format, must be \"dd:MM:yyyy HH:mm\"");
    }
}