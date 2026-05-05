package ru.game;

import ru.game.field.Field;
import ru.game.field.FieldImpl;
import ru.game.input.Input;
import ru.game.input.InputImpl;
import ru.game.model.Player;
import ru.game.model.PlayerImpl;
import ru.game.output.Output;
import ru.game.output.OutputImpl;
import ru.game.validator.Validator;
import ru.game.validator.ValidatorImpl;

public class Main {
    public static void main(String[] args) {
        Game game = initGame();
        game.start();
    }

    private static Game initGame() {
        Validator validator = new ValidatorImpl();
        Input in = new InputImpl(validator);
        Output out = new OutputImpl(validator);
        Player first = new PlayerImpl('X', in);
        Player second = new PlayerImpl('O', in);
        Field field = new FieldImpl();
        return new GameMaster(first, second, field, out, validator);
    }
}
