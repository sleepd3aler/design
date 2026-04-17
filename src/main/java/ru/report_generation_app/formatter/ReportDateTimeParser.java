package ru.report_generation_app.formatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportDateTimeParser implements DateTimeParser<Calendar> {

    @Override
    public String parse(Calendar calendar, String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(calendar.getTime());
    }
}
