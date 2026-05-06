package ru.game.validator;

import ru.game.field.Field;

public interface Validator {
    void validateInput(String input);

    void validateField(Field field);

    void validateMove(Field field, int position);
}
