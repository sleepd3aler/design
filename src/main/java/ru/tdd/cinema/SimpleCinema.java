package ru.tdd.cinema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleCinema implements Cinema {
    private final Map<Integer, Movie> storage = new HashMap<>();
    private int ids = 0;

    @Override
    public Movie addToList(Movie movie) {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public Movie findByName(String name) {
        return null;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void rateMovie(int rate) {

    }

    @Override
    public List<Movie> findByGenre(String genre) {
        return List.of();
    }

    @Override
    public List<Movie> showMovieList() {
        return List.of();
    }

    @Override
    public void playMovie() {

    }

    @Override
    public void stopPlaying() {

    }
}
