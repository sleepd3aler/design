package ru.report_generation_app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.report_generation_app.exceptions.GenerationException;

public class ValidatorUtils {

    private ValidatorUtils() {
    }

    public static void checkDateTime(Pattern pattern, String report, String dateFormat) {
        Matcher matcher = pattern.matcher(report);
        boolean found = false;
        matcher.reset();
        while (matcher.find()) {
            checkDateTime(matcher.group(2), dateFormat);
            found = true;
        }
        if (!found) {
            throw new GenerationException("Date is missing");
        }
    }

    public static void checkDateTime(String date, String dateFormat) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
        try {
            dateFormatter.parse(date);
        } catch (ParseException e) {
            throw new GenerationException("Illegal Date : Time format, must be \"" + dateFormat + "\"");
        }
    }
}
