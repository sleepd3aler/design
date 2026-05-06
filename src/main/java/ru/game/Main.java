package ru.game;

import java.util.Scanner;
import ru.game.field.Field;
import ru.game.field.FieldImpl;
import ru.game.formatters.FieldFormatter;
import ru.game.input.Input;
import ru.game.input.InputImpl;
import ru.game.model.Player;
import ru.game.model.PlayerImpl;
import ru.game.output.Output;
import ru.game.output.OutputImpl;
import ru.game.rules.Rules;
import ru.game.rules.SimpleRule;
import ru.game.validator.Validator;
import ru.game.validator.ValidatorImpl;

public class Main {
    public static void main(String[] args) {
        Game game = initGame();
        game.start();
    }

    private static Game initGame() {
        Validator validator = new ValidatorImpl();
        Rules rules = new SimpleRule();
        Input in = new InputImpl(validator, new Scanner(System.in));
        Output out = new OutputImpl(validator, new FieldFormatter());
        Player first = new PlayerImpl('X', in);
        Player second = new PlayerImpl('O', in);
        Field field = new FieldImpl();
        return new GameMaster(rules, first, second, field, out, validator);
    }
}
