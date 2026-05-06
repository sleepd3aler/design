package ru.game.output;

import java.util.List;
import ru.game.formatters.FieldFormatter;
import ru.game.validator.Validator;

public class OutputImpl implements Output {
    private Validator validator;
    private FieldFormatter formatter;

    public OutputImpl(Validator validator, FieldFormatter formatter) {
        this.validator = validator;
        this.formatter = formatter;
    }

    @Override
    public void showField(List<Character> field) {
        validator.validateField(field);
        System.out.println(formatter.generateField(field));
    }

    @Override
    public void printMsg(String message) {
        System.out.println(message);
    }
}
