package ru.game.field;

import java.util.List;

public interface Field {
    List<Character> getField();

    void placeSign(int place, char sign);
}
