package ru.tdd.userService;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserServiceTest {
    UserService service = new UserService();

    @Test
    void whenFindByIdThenExpectedResult() {
        int id = 6;
        String expected = "Ivan: 6";
        assertThat(service.getUsernameById(id)).isEqualTo(expected);
    }

    @Test
    void whenFindByZeroIdThenExceptionThrown() {
        int id = 0;
        assertThatThrownBy(() -> service.getUsernameById(id))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenFindByNegativeThenExceptionThrown() {
        int id = -1;
        assertThatThrownBy(() -> service.getUsernameById(id))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenFindByOneThenExpectedResult() {
        int id = 1;
        String expected = "Sergey: 1";
        assertThat(service.getUsernameById(id)).isEqualTo(expected);
    }
}