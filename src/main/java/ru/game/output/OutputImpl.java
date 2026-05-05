package ru.game.output;

import java.util.List;
import ru.game.validator.Validator;

public class OutputImpl implements Output {
    private Validator validator;

    public OutputImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void showField(List<Character> field) {
        validator.validateField(field);
        System.out.println(generateField(field));
    }

    private String generateField(List<Character> field) {
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
