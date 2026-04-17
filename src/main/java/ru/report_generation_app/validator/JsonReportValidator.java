package ru.report_generation_app.validator;

import java.util.regex.Pattern;
import ru.report_generation_app.exceptions.GenerationException;

import static ru.report_generation_app.utils.ValidatorUtils.checkDateTime;

public class JsonReportValidator implements Validator {
    Pattern pattern = Pattern.compile("(\"hired\"|\"fired\"):\\s*\"([^\"]+)\"");

    @Override
    public void validateReport(String report, String dateFormat) {
        checkJsonFormat(report);
        checkDateTime(pattern, report, dateFormat);
    }

    private void checkJsonFormat(String string) {
        if (string.isBlank() || !string.contains(System.lineSeparator())) {
            throw new GenerationException("Json report has wrong format. Set pretty printing");
        }
    }
}
