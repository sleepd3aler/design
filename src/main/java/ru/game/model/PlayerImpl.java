package ru.game.model;

import ru.game.input.Input;

public class PlayerImpl implements Player {
    private char sign;
    private Input input;

    public PlayerImpl(char sign, Input input) {
        this.sign = sign;
        this.input = input;
    }

    @Override
    public int makeMove() {
        return Integer.parseInt(input.readInput());
    }

    @Override
    public char getSign() {
        return sign;
    }
}
