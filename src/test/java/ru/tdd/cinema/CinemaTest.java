package ru.tdd.cinema;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tdd.cinema.exceptions.MovieNotFound;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CinemaTest {
    private Cinema cinema;
    private Movie first;
    private Movie second;
    private Movie third;
    private Movie fourth;
    private Movie fifth;
    private Movie sixth;

    @BeforeEach
    void setUp() {
        cinema = new SimpleCinema();
        first = new Movie("I saw the devil", "Drama");
        second = new Movie("The game", "Thriller");
        third = new Movie("We are the Millers", "Comedy");
        fourth = new Movie("Machine Gun Preacher", "Action");
        fifth = new Movie("Attack on titan", "Anime");
        sixth = new Movie("Berserk", "Anime");

    }

    @Test
    void whenAddStorageContainsMovie() {
        Movie expected = cinema.findByName("We are the Millers");
        expected.setId(1);
        Movie added = cinema.addToList(third);
        assertThat(added).isEqualTo(expected);
    }

    @Test
    void whenAddNullThenExceptionThrown() {
        assertThatThrownBy(() -> cinema.addToList(null)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Movie adding failed");
    }

    @Test
    void whenFindByNameAndStorageDoesntContainsMovieThenExceptionThrown() {
        assertThatThrownBy(() -> cinema.findByName(first.getName()))
                .isInstanceOf(MovieNotFound.class)
                .hasMessageContaining("Current movie is missing.");
    }

    @Test
    void whenRemoveThenCinemaDoesntContains() {
        cinema.addToList(first);
        cinema.remove(first.getId());
        assertThat(cinema.findByName(first.getName())).isNull();
    }

    @Test
    void whenRemoveByIncorrectIdThenExceptionThrown() {
        assertThatThrownBy(() -> cinema.remove(first.getId())).isInstanceOf(MovieNotFound.class)
                .hasMessageContaining("Current movie is missing.");
    }

    @Test
    void whenFindByGenreThenExpectedResult() {
        cinema.addToList(first);
        cinema.addToList(second);
        cinema.addToList(third);
        cinema.addToList(fourth);
        cinema.addToList(fifth);
        cinema.addToList(sixth);
        List<Movie> expected = List.of(fifth, sixth);
        List<Movie> res = cinema.findByGenre("Anime");
        assertThat(res).containsOnly(fifth, sixth);
    }

    @Test
    void whenShowMovieListThenExpectedResult() {
        cinema.addToList(first);
        cinema.addToList(second);
        cinema.addToList(third);
        cinema.addToList(fourth);
        cinema.addToList(fifth);
        cinema.addToList(sixth);
        List<Movie> expected = List.of(first, second, third, fourth, fifth, sixth);
        List<Movie> res = cinema.showMovieList();
        assertThat(res).hasSize(6)
                .containsExactly(first, second, third, fourth, fifth, sixth);
    }
}