package ru.game.input;

import java.util.Scanner;
import ru.game.validator.Validator;

public class InputImpl implements Input {
    private Scanner scanner = new Scanner(System.in);
    private Validator validator;

    public InputImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public String readInput() {
        String input = scanner.nextLine();
        validator.validateInput(input);
        return input;
    }
}
