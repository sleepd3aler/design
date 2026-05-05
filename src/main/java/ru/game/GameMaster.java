package ru.game;

import java.util.List;
import ru.game.field.Field;
import ru.game.model.Player;
import ru.game.output.Output;
import ru.game.validator.Validator;

public class GameMaster implements Game {
    private Player first;
    private Player second;
    private Field field;
    private Output output;
    private Validator validator;

    public GameMaster(Player first, Player second, Field field, Output output, Validator validator) {
        this.first = first;
        this.second = second;
        this.field = field;
        this.output = output;
        this.validator = validator;
    }

    @Override
    public void start() {
        System.out.println("Game begins!");
        boolean run = true;
        while (run) {
            System.out.println("First player`s turn!");
            System.out.println("Enter position in [1 - 9]");
            tryMakeMove(first, first.getSign());
            showField();
            run = checkWinner(field.getField(), first.getSign());
            if (!run) {
                break;
            }
            System.out.println("Second player`s turn!");
            System.out.println("Enter position in [1 - 9]");
            tryMakeMove(second, second.getSign());
            showField();
            run = checkWinner(field.getField(), second.getSign());
        }
    }

    private void tryMakeMove(Player player, char sign) {
        List<Character> currentField = field.getField();
        while (true) {
            try {
                int slot = player.makeMove();
                validator.validateMove(currentField, slot);
                field.placeSign(slot, sign);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println(sign + "`s Try again!");
            }
        }
    }

    @Override
    public void showField() {
        output.showField(field.getField());
    }

    @Override
    public boolean checkWinner(List<Character> field, char sign) {
        if (foundInRows(field, sign)) {
            System.out.println(sign + " wins");
            return false;
        }
        if (foundInCols(field, sign)) {
            System.out.println(sign + " wins");
            return false;
        }
        if (foundByDiagonal(field, sign)) {
            System.out.println(sign + " wins");
            return false;
        }
        if (isStandoff(field)) {
            System.out.println("Nobody wins!");
            return false;
        }
        return true;
    }

    private boolean isStandoff(List<Character> field) {
        return !field.contains(' ');
    }

    private boolean foundInRows(List<Character> field, char sign) {
        return (field.get(0).equals(sign) && field.get(1).equals(sign) && field.get(2).equals(sign)) || (field.get(3).equals(sign) && field.get(4).equals(sign) && field.get(5).equals(sign)) || (field.get(6).equals(sign) && field.get(7).equals(sign) && field.get(8).equals(sign));
    }

    private boolean foundInCols(List<Character> field, char sign) {
        return (field.get(0).equals(sign) && field.get(3).equals(sign) && field.get(6).equals(sign)) || (field.get(1).equals(sign) && field.get(4).equals(sign) && field.get(7).equals(sign)) || (field.get(2).equals(sign) && field.get(5).equals(sign) && field.get(8).equals(sign));
    }

    private boolean foundByDiagonal(List<Character> field, char sign) {
        return (field.get(0).equals(sign) && field.get(4).equals(sign) && field.get(8).equals(sign)) || (field.get(2).equals(sign) && field.get(4).equals(sign) && field.get(6).equals(sign));

    }
}
