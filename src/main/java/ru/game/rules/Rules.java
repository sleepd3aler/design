package ru.game.rules;

import ru.game.field.Field;

public interface Rules {

    boolean isStandoff(Field field);

    boolean isFoundInRows(Field field, char sign);

    boolean isFoundInCols(Field field, char sign);

    boolean isFoundByDiagonal(Field field, char sign);
}
