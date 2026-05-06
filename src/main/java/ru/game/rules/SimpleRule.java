package ru.game.rules;

import java.util.List;
import ru.game.field.Field;

public class SimpleRule implements Rules {

    @Override
    public boolean isStandoff(Field field) {
        List<Character> current = field.getField();
        return !current.contains(' ');
    }

    @Override
    public boolean isFoundInRows(Field field, char sign) {
        List<Character> current = field.getField();
        return isThreeInARow(current, sign, 1, 2, 3, 5, 6, 7);
    }

    @Override
    public boolean isFoundInCols(Field field, char sign) {
        List<Character> current = field.getField();
        return isThreeInARow(current, sign, 3, 6, 1, 7, 2, 5);
    }

    @Override
    public boolean isFoundByDiagonal(Field field, char sign) {
        List<Character> current = field.getField();

        return (current.get(0).equals(sign) && current.get(4).equals(sign) && current.get(8).equals(sign))
                || (current.get(2).equals(sign) && current.get(4).equals(sign) && current.get(6).equals(sign));

    }

    private boolean isThreeInARow(List<Character> field, char sign, int pos1, int pos2, int pos3, int pos4, int pos5, int pos6) {
        return (field.get(0).equals(sign) && field.get(pos1).equals(sign) && field.get(pos2).equals(sign))
                || (field.get(pos3).equals(sign) && field.get(4).equals(sign) && field.get(pos4).equals(sign))
                || (field.get(pos5).equals(sign) && field.get(pos6).equals(sign) && field.get(8).equals(sign));
    }
}
