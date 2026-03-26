package ru.tdd.cinema.model;

import java.util.Objects;

public class Ticket {
    private int id;
    private String nameOfMovie;
    private int price;

    public Ticket(String nameOfMovie, int price) {
        this.nameOfMovie = nameOfMovie;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNameOfMovie() {
        return nameOfMovie;
    }

    public void setNameOfMovie(String nameOfMovie) {
        this.nameOfMovie = nameOfMovie;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", nameOfMovie='" + nameOfMovie + '\'' +
                ", price=" + price +
                '}';
    }
}
