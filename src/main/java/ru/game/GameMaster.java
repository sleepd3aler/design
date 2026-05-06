package ru.game;

import ru.game.field.Field;
import ru.game.input.Input;
import ru.game.model.Player;
import ru.game.output.Output;
import ru.game.rules.Rules;
import ru.game.validator.Validator;

public class GameMaster implements Game {
    private Rules rules;
    private Player first;
    private Player second;
    private Field field;
    private Input input;
    private Output output;
    private Validator validator;

    public GameMaster(Rules rules, Player first, Player second, Field field, Input input, Output output, Validator validator) {
        this.rules = rules;
        this.first = first;
        this.second = second;
        this.field = field;
        this.input = input;
        this.output = output;
        this.validator = validator;
    }

    @Override
    public void start() {
        output.printMsg("Game begins!");
        boolean run = true;
        while (run) {
            output.printMsg("First player`s turn!");
            output.printMsg("Enter position in [1 - 9]");
            makeMove(field, first.getSign());
            validator.validateField(field);
            showField();
            run = checkWinner(field, first.getSign());
            if (!run) {
                break;
            }
            output.printMsg("Second player`s turn!");
            output.printMsg("Enter position in [1 - 9]");
            makeMove(field, second.getSign());
            validator.validateField(field);
            showField();
            run = checkWinner(field, second.getSign());
        }
    }

    @Override
    public void showField() {
        output.showField(field.getField());
    }

    @Override
    public boolean checkWinner(Field field, char sign) {
        if (rules.isFoundInRows(field, sign)) {
            output.printMsg(sign + " wins");
            return false;
        }
        if (rules.isFoundInCols(field, sign)) {
            output.printMsg(sign + " wins");
            return false;
        }
        if (rules.isFoundByDiagonal(field, sign)) {
            output.printMsg(sign + " wins");
            return false;
        }
        if (rules.isStandoff(field)) {
            output.printMsg("Nobody wins!");
            return false;
        }
        return true;
    }

    private void makeMove(Field field, char sign) {
        while (true) {
            try {
                String in = input.readInput();
                validator.validateInput(in);
                int slot = Integer.parseInt(in);
                validator.validateMove(field, slot);
                field.placeSign(slot, sign);
                break;
            } catch (IllegalArgumentException e) {
                output.printMsg(e.getMessage());
                output.printMsg(sign + "`s Try again!");
            }
        }
    }

}
