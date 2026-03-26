package ru.tdd.cinema;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tdd.cinema.exceptions.MovieNotFound;
import ru.tdd.cinema.model.Movie;
import ru.tdd.cinema.model.Ticket;

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

    private Ticket firstTicket;
    private Ticket secondTicket;
    private Ticket thirdTicket;

    @BeforeEach
    void setUp() {
        cinema = new SimpleCinema();
        first = new Movie("I saw the devil", "Drama");
        second = new Movie("The game", "Thriller");
        third = new Movie("We are the Millers", "Comedy");
        fourth = new Movie("Machine Gun Preacher", "Action");
        fifth = new Movie("Attack on titan", "Anime");
        sixth = new Movie("Berserk", "Anime");
        firstTicket = new Ticket(first.getName(), 500);
        secondTicket = new Ticket(second.getName(), 500);
        firstTicket = new Ticket(third.getName(), 500);

    }

    @Test
    void whenAddStorageContainsMovie() {
        Movie added = cinema.addToList(third);
        Movie expected = cinema.findByName("We are the Millers");
        expected.setId(1);
        assertThat(added).isEqualTo(expected);
    }

    @Test
    void whenAddNullThenExceptionThrown() {
        assertThatThrownBy(() -> cinema.addToList(null)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Movie adding failed, non null require");
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
        assertThatThrownBy(() -> cinema.findByName(first.getName()))
                .isInstanceOf(MovieNotFound.class);
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

    @Test
    void whenMovieNotExistsThenMovieNotFoundThrown() {
        assertThatThrownBy(() -> cinema.rateMovie(third, 5))
                .isInstanceOf(MovieNotFound.class)
                .hasMessageContaining("Current movie is missing.");
    }

    @Test
    void whenSellTicketThenSoldListContainsIt() {
        cinema.addToList(first);
        cinema.addToList(second);
        cinema.addToList(third);
        Ticket ticketOne = cinema.sellTicket(first.getName(), 500);
        Ticket ticketTwo = cinema.sellTicket(third.getName(), 500);
        List<Ticket> expected = List.of(ticketOne, ticketTwo);
        assertThat(cinema.getSoldTickets()).isEqualTo(expected);
    }

    @Test
    void whenTryingToBuyATicketWithMissingMovieThenExceptionThrown() {
        assertThatThrownBy(() -> cinema.sellTicket(first.getName(), 500))
                .isInstanceOf(MovieNotFound.class)
                .hasMessageContaining("Current movie is missing.");
    }

    @Test
    void whenTryingToBuyATicketAndIllegalMoneyAmountProvidedThenExceptionThrown() {
        cinema.addToList(fifth);
        Ticket ticket = new Ticket(fifth.getName(), 500);
        assertThatThrownBy(() -> cinema.sellTicket(fifth.getName(), 400))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Not enough money for buying ticket. Ticket price is : " + ticket.getPrice());
    }

    @Test
    void whenTakeBackTicketMoneyReturnedAndTicketRemovedFromStorage() {
        cinema.addToList(fifth);
        cinema.addToList(first);
        Ticket toReturn = new Ticket(fifth.getName(), 500);
        cinema.sellTicket(toReturn.getNameOfMovie(), 500);
        int refund = cinema.takeBackTicket(toReturn);
        List<Ticket> result = cinema.getSoldTickets();
        assertThat(refund).isEqualTo(500);
        assertThat(result).doesNotContain(toReturn);
    }
}