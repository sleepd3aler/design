package ru.tdd.cinema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.tdd.cinema.exceptions.MovieNotFound;
import ru.tdd.cinema.model.Movie;
import ru.tdd.cinema.model.Ticket;

public class SimpleCinema implements Cinema {
    private final Map<Integer, Movie> movieStorage = new HashMap<>();
    private final Map<Integer, Ticket> ticketStorage = new HashMap<>();

    private int price = 500;
    private int ids = 0;

    @Override
    public Movie addToList(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie adding failed, non null require");
        }
        movieStorage.put(ids++, movie);
        return movie;
    }

    @Override
    public boolean remove(int id) {
        if (!movieStorage.containsKey(id)) {
            throw new MovieNotFound("Current movie is missing.");
        }
        return movieStorage.remove(id) != null;
    }

    @Override
    public Movie findByName(String name) {
        return movieStorage.values()
                .stream()
                .filter(movie -> movie.getName().equals(name))
                .findFirst().orElseThrow(() -> new MovieNotFound("Current movie is missing."));
    }

    @Override
    public String getDescription(Movie movie) {
        return "";
    }

    @Override
    public void rateMovie(Movie movie, int rate) {
        if (!movieStorage.containsValue(movie)) {
            throw new MovieNotFound("Current movie is missing.");
        }
        movie.setRate(rate);
    }

    @Override
    public List<Movie> findByGenre(String genre) {
        return movieStorage.values()
                .stream()
                .filter(movie -> movie.getGenre().equals(genre))
                .toList();
    }

    @Override
    public List<Movie> showMovieList() {
        return new ArrayList<>(movieStorage.values());
    }

    @Override
    public Ticket sellTicket(String movieName, int money) {
        validateTrade(movieName, money);
        Ticket ticket = new Ticket(movieName, price);
        ticket.setId(ids);
        ticketStorage.put(ids++, ticket);
        return ticket;
    }

    @Override
    public int takeBackTicket(Ticket ticket) {
        checkExists(ticket.getNameOfMovie());
        ticketStorage.remove(ticket.getId());
        return ticket.getPrice();
    }

    @Override
    public List<Ticket> getSoldTickets() {
        return new ArrayList<>(ticketStorage.values());
    }

    @Override
    public void playMovie() {
    }

    @Override
    public void stopPlaying() {

    }

    private boolean checkExists(String movieName) {
        return findByName(movieName) != null;
    }

    private void validateTrade(String movieName, int money) {
        checkExists(movieName);
        if (money < price) {
            throw new IllegalArgumentException("Not enough money for buying ticket. Ticket price is : " + price);
        }
    }
}
