package ru.game;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.game.field.Field;
import ru.game.field.FieldImpl;
import ru.game.formatters.FieldFormatter;
import ru.game.input.Input;
import ru.game.input.MockInput;
import ru.game.model.Player;
import ru.game.model.PlayerImpl;
import ru.game.output.MockOutput;
import ru.game.rules.Rules;
import ru.game.rules.SimpleRule;
import ru.game.validator.Validator;
import ru.game.validator.ValidatorImpl;

import static org.assertj.core.api.Assertions.assertThat;

class GameMasterTest {
    private Input in;
    private MockOutput out;
    private Player first;
    private Player second;
    private Validator validator;
    private Rules rules;
    private Field field;
    private FieldFormatter formatter;
    private Game game;

    @BeforeEach
    void setUp() {
        this.formatter = new FieldFormatter();
        this.out = new MockOutput();
        this.field = new FieldImpl();
        this.first = new PlayerImpl('X');
        this.second = new PlayerImpl('O');
        this.rules = new SimpleRule();
        this.validator = new ValidatorImpl();
    }

    @Test
    void checkXWinsIfInAFirstRow() {
        in = new MockInput(List.of("1", "4", "2", "5", "3"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).endsWith("X wins\n");
    }

    @Test
    void checkXWinsIfInAFirstCol() {
        in = new MockInput(List.of("1", "2", "4", "5", "7"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).endsWith("X wins\n");
    }

    @Test
    void checkOWinsIfInASecondRow() {
        in = new MockInput(List.of("1", "4", "2", "5", "9", "6"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).endsWith("O wins\n");
    }

    @Test
    void checkOWinsIfInASecondCol() {
        in = new MockInput(List.of("1", "2", "4", "5", "9", "8"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).endsWith("O wins\n");
    }

    @Test
    void checkXWinsIfInAThirdRow() {
        in = new MockInput(List.of("7", "4", "8", "5", "9"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).endsWith("X wins\n");
    }

    @Test
    void checkXWinsIfInAThirdCol() {
        in = new MockInput(List.of("3", "4", "6", "5", "9"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).endsWith("X wins\n");
    }

    @Test
    void checkXWinsIf3InDiagonal() {
        in = new MockInput(List.of("1", "4", "5", "6", "9"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).endsWith("X wins\n");
    }

    @Test
    void checkOWinsIf3InADiagonal() {
        in = new MockInput(List.of("1", "3", "2", "5", "8", "7"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).endsWith("O wins\n");
    }

    @Test
    void checkStandOff() {
        in = new MockInput(List.of("1", "4", "2", "5", "9", "8", "7", "3", "6"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).endsWith("Nobody wins!\n");
    }

    @Test
    void whenPlayerChoseBusyPositionThenGameAsksTryAgain() {
        in = new MockInput(List.of("1", "1", "4", "4", "2", "5", "3"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg())
                .contains("Current position is busy!")
                .contains("O`s Try again!")
                .contains("X`s Try again!");
    }

    @Test
    void whenPlayersInputIsNotDigitThenGameAskToTryAgainWithSpecifiedMessage() {
        in = new MockInput(List.of("s", "1", "4", "2", "5", "3"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).contains("Input must be a digit in [1 - 9].")
                .contains("X`s Try again!");
    }

    @Test
    void whenPlayersInputIsSpaceThenThenGameAskToTryAgainWithSpecifiedMessage() {
        in = new MockInput(List.of(" ", "1", "4", "2", "5", "3"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).contains("Input number of position in [1 - 9].")
                .contains("X`s Try again!");
    }

    @Test
    void whenPlayersInputIsZeroThenThenGameAskToTryAgainWithSpecifiedMessage() {
        in = new MockInput(List.of("1", "0", "4", "2", "5", "3"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).contains("Position must be in [1 - 9].")
                .contains("O`s Try again!");
    }

    @Test
    void whenPlayersInputIsMoreThan9ThenThenGameAskToTryAgainWithSpecifiedMessage() {
        in = new MockInput(List.of("1", "11", "4", "2", "5", "3"));
        game = new GameMaster(rules, first, second, field, in, out, validator);
        game.start();
        assertThat(out.getMsg()).contains("Input must contain only position info.")
                .contains("O`s Try again!");
    }

}