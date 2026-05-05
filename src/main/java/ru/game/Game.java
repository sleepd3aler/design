package ru.game;

import java.util.List;

public interface Game {
    void start();

    void showField();

    boolean checkWinner(List<Character> field, char sign);
}
