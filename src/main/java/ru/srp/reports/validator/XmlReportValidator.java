package ru.srp.reports.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.srp.reports.exceptions.GenerationException;

public class XmlReportValidator implements Validator {
    Pattern pattern = Pattern.compile("(<hired>|<fired>)\\s*([^<]+)\\s*</");
    private  String format;

    public XmlReportValidator(String format) {
        this.format = format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public void validateReport(String report) {
        checkXmlFormat(report);
        checkDateTime(report);
    }

    private void checkXmlFormat(String report) {
        String header = report.split("\n")[0];
        if (report.isBlank() || !header.startsWith("<?xml")) {
            throw new GenerationException("Illegal report generated, topic must be \"<?xml version ...\"");
        }

        if (!report.contains(System.lineSeparator())) {
            throw new GenerationException("Xml report has wrong format. Set pretty printing");
        }
    }

    private void parseDateTimeFormat(String report) {
        SimpleDateFormat check = new SimpleDateFormat(format);
        try {
            check.parse(report);
        } catch (ParseException e) {
            throw new GenerationException("Illegal Date : Time format, must be \"dd:MM:yyyy HH:mm\"");
        }
    }

    private void checkDateTime(String report) {
        Matcher matcher = pattern.matcher(report);
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
