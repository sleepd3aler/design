package ru.game.output;

import java.util.List;
import java.util.Objects;

public class MockOutput implements Output {
    StringBuilder buffer = new StringBuilder();

    @Override
    public void showField(List<Character> field) {

    }

    @Override
    public void printMsg(String message) {
        buffer.append(Objects.requireNonNullElse(message, "null"));
        buffer.append(System.lineSeparator());

    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    public StringBuilder getMsg() {
        return buffer;
    }
}
