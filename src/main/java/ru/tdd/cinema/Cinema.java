package ru.tdd.cinema;

import java.util.List;

public interface Cinema {
    Movie addToList(Movie movie);

    boolean remove(int id);

    Movie findByName(String name);

    String getDescription();

    void rateMovie(int rate);

    List<Movie> findByGenre(String genre);

    List<Movie> showMovieList();

    void playMovie();

    void stopPlaying();

}
