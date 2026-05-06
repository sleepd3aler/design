package ru.game.model;

public class PlayerImpl implements Player {
    private char sign;

    public PlayerImpl(char sign) {
        this.sign = sign;
    }

    @Override
    public char getSign() {
        return sign;
    }
}
