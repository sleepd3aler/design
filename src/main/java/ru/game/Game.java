package ru.game;

import ru.game.field.Field;

public interface Game {
    void start();

    void showField();

    boolean checkWinner(Field field, char sign);
}
