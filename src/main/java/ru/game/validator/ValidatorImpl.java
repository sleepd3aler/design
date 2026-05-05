package ru.game.validator;

import java.util.List;

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
    public void validateField(List<Character> field) {
        if (field.size() != 9) {
            throw new IllegalArgumentException("Field might contain only [9] positions");
        }
        for (Character position : field) {
            if (!(position.equals('X') || position.equals('O') || position.equals(' '))) {
                throw new IllegalArgumentException("Field might contain only : X, O, or backspace.");
            }
        }
    }

    @Override
    public void validateMove(List<Character> field, int position) {
        if (!field.get(position - 1).equals(' ')) {
            throw new IllegalArgumentException("Current position is busy!");
        }
    }
}
