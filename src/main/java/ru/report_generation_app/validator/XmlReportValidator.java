package ru.report_generation_app.validator;

import java.util.regex.Pattern;
import ru.report_generation_app.exceptions.GenerationException;

import static ru.report_generation_app.utils.ValidatorUtils.checkDateTime;

public class XmlReportValidator implements Validator {
    private final Pattern pattern = Pattern.compile("(<hired>|<fired>)\\s*([^<]+)\\s*</");

    @Override
    public void validateReport(String report, String format) {
        checkXmlFormat(report);
        checkDateTime(pattern, report, format);
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
}
