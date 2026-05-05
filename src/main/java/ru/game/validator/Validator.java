package ru.game.validator;

import java.util.List;

public interface Validator {
    void validateInput(String input);

    void validateField(List<Character> field);

    void validateMove(List<Character> field, int position);
}
