package ru.report_generation_app.validator;

import ru.report_generation_app.exceptions.GenerationException;

public class HrReportValidator implements Validator {

    @Override
    public void validateReport(String report, String dateFormat) {
        String[] parts = report.split("\n");
        if (parts[0].isBlank()) {
            throw new GenerationException("Topic is missing");
        }
        for (int i = 1; i < parts.length; i++) {
            String[] content = parts[i].split(" ");
            if (!checkContent(content)) {
                throw new GenerationException("Employee info must contain Name and Salary only, and cant be empty.");
            }
        }
    }

    private boolean checkContent(String[] content) {
        return content.length >= 2;
    }
}
