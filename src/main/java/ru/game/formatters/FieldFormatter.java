package ru.game.formatters;

import java.util.List;

public class FieldFormatter implements Formatter {
    @Override
    public String generateField(List<Character> field) {
        StringBuilder fieldBuilder = new StringBuilder();
        fieldBuilder.append(" ___".repeat(3)).append("\n");
        for (int i = 0; i < field.size(); i++) {
            fieldBuilder.append("|").append(" ").append(field.get(i)).append(" ");
            if ((i + 1) % 3 == 0) {
                fieldBuilder.append("|").append("\n");
            }
        }
        fieldBuilder.append(" ‾‾‾".repeat(3));
        return fieldBuilder.toString();
    }
}
