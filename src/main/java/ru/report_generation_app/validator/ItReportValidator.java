package ru.report_generation_app.validator;

import ru.report_generation_app.exceptions.GenerationException;

public class ItReportValidator implements Validator {
    @Override
    public void validateReport(String report) {
        String[] parts = report.split("\n");
        if (parts[0].isBlank()) {
            throw new GenerationException("Topic is missing");
        }
        for (int i = 1; i < parts.length; i++) {
            String[] content = parts[i].split(",");
            if (!checkContent(content)) {
                throw new GenerationException("Employee info must contain Name and Salary only, and cant be empty.");
            }
        }
    }

    private boolean checkContent(String[] content) {
        return content.length == 4;
    }

    public static void main(String[] args) {
        ItReportValidator validator = new ItReportValidator();
        validator.validateReport("asd asd");
    }
}
