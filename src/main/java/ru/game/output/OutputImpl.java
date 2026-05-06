package ru.game.output;

import java.util.List;
import ru.game.formatters.FieldFormatter;

public class OutputImpl implements Output {
    private FieldFormatter formatter;

    public OutputImpl(FieldFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void showField(List<Character> field) {
        System.out.println(formatter.generateField(field));
    }

    @Override
    public void printMsg(String message) {
        System.out.println(message);
    }
}
