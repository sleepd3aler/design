package ru.tdd.cinema;

import java.util.List;
import ru.tdd.cinema.model.Movie;
import ru.tdd.cinema.model.Ticket;

public interface Cinema {
    Movie addToList(Movie movie);

    boolean remove(int id);

    Movie findByName(String name);

    String getDescription(Movie movie);

    void rateMovie(Movie movie, int rate);

    List<Movie> findByGenre(String genre);

    List<Movie> showMovieList();

    Ticket sellTicket(String movieName, int money);

    int takeBackTicket(Ticket ticket);

    List<Ticket> getSoldTickets();

    void playMovie();

    void stopPlaying();

}
