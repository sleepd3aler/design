package ru.game.input;

import java.util.List;

public class MockInput implements Input {
    List<String> answers;

    int position;

    public MockInput(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String readInput() {
        return answers.get(position++);
    }
}
