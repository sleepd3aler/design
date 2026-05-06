package ru.game.input;

import java.util.Scanner;
import ru.game.validator.Validator;

public class InputImpl implements Input {
    private Scanner scanner;
    private Validator validator;

    public InputImpl(Validator validator, Scanner scanner) {
        this.validator = validator;
        this.scanner = scanner;
    }

    @Override
    public String readInput() {
        String input = scanner.nextLine();
        validator.validateInput(input);
        return input;
    }
}
