package ru.game.field;

import java.util.ArrayList;
import java.util.List;

public class FieldImpl implements Field {
    private List<Character> field = new ArrayList<>();

    public FieldImpl() {
        initField();
    }

    @Override
    public List<Character> getField() {
        return field;
    }

    @Override
    public void placeSign(int place, char sign) {
        field.set(place - 1, Character.toUpperCase(sign));
    }

    private void initField() {
        for (int i = 0; i < 9; i++) {
            field.add(' ');
        }
    }
}
