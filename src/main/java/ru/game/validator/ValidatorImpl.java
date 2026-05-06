package ru.game.validator;

import java.util.List;
import ru.game.field.Field;

public class ValidatorImpl implements Validator {
    @Override
    public void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input number of position in [1 - 9].");
        }
        if (input.length() != 1) {
            throw new IllegalArgumentException("Input must contain only position info.");
        }
        try {
            int position = Integer.parseInt(input);
            if (position < 1 || position > 9) {
                throw new IllegalArgumentException("Position must be in [1 - 9].");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a digit in [1 - 9].");
        }
    }

    @Override
    public void validateField(Field field) {
        List<Character> current = field.getField();
        if (current.size() != 9) {
            throw new IllegalArgumentException("Field might contain only [9] positions");
        }
        for (Character position : current) {
            if (!(position.equals('X') || position.equals('O') || position.equals(' '))) {
                throw new IllegalArgumentException("Field might contain only : X, O, or backspace.");
            }
        }
    }

    @Override
    public void validateMove(Field field, int position) {
        List<Character> current = field.getField();
        if (!current.get(position - 1).equals(' ')) {
            throw new IllegalArgumentException("Current position is busy!");
        }
    }
}
