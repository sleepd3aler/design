package ru.game.output;

import java.util.List;

public interface Output {
    void showField(List<Character> field);

    void printMsg(String message);
}
