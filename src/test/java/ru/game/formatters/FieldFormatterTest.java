package ru.game.formatters;

import org.junit.jupiter.api.Test;
import ru.game.field.Field;
import ru.game.field.FieldImpl;

import static org.assertj.core.api.Assertions.assertThat;

class FieldFormatterTest {

    @Test
    void test() {
        Formatter formatter = new FieldFormatter();
        Field field = new FieldImpl();
        field.placeSign(1, 'x');
        field.placeSign(2, 'o');
        field.placeSign(3, 'x');
        field.placeSign(4, 'o');
        field.placeSign(5, 'o');
        field.placeSign(6, 'x');
        field.placeSign(7, 'o');
        field.placeSign(8, 'x');
        field.placeSign(9, 'o');
        String expected = """
                 ___ ___ ___
                | X | O | X |
                | O | O | X |
                | O | X | O |
                 ‾‾‾ ‾‾‾ ‾‾‾""";
        String res = formatter.generateField(field.getField());
        assertThat(res).isEqualTo(expected);
    }

}