package ru.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FoolTest {
    @Test
    void when3ThenAnswerFizz() {
        String res = Fool.checkNum(3);
        assertThat(res).isEqualTo("Fizz");
    }

    @Test
    void when5ThenAnswerBuzz() {
        String res = Fool.checkNum(5);
        assertThat(res).isEqualTo("Buzz");
    }

    @Test
    void when15ThenAnswerFizzBuzz() {
        String res = Fool.checkNum(15);
        assertThat(res).isEqualTo("FizzBuzz");
    }

    @Test
    void when7ThenAnswer7() {
        String res = Fool.checkNum(7);
        assertThat(res).isEqualTo("7");
    }

}