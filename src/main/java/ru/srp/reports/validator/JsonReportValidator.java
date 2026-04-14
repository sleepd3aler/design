package ru.srp.reports.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.srp.reports.exceptions.GenerationException;

public class JsonReportValidator implements Validator {
    Pattern pattern = Pattern.compile("(\"hired\"|\"fired\"):\\s*\"([^\"]+)\"");

    @Override
    public void validateReport(String report) {
        checkJsonFormat(report);
        checkDateTime(report);
    }

    private void checkJsonFormat(String string) {
        if (string.isBlank() || !string.contains(System.lineSeparator())) {
            throw new GenerationException("Json report has wrong format. Set pretty printing");
        }
    }

    private void parseDateTimeFormat(String string) {
        SimpleDateFormat check = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        try {
            check.parse(string);
        } catch (ParseException e) {
            throw new GenerationException("Illegal Date : Time format, must be \"dd:MM:yyyy HH:mm\"");
        }
    }

    private void checkDateTime(String string) {
        Matcher matcher = pattern.matcher(string);
        if (!matcher.find()) {
            throw new GenerationException("Illegal Date : Time format, must be \"dd:MM:yyyy HH:mm\"");
        }
        matcher.reset();
        while (matcher.find()) {
            String dateTime = matcher.group(2);
            parseDateTimeFormat(dateTime);
        }
    }
}
