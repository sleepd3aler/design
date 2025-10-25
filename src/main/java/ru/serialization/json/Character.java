package ru.serialization.json;

import java.util.Arrays;

public class Character {
    private final boolean online;
    private final int lvl;
    private final String nickname;
    private final Pet pet;
    private final String[]friends;

    public Character(boolean online, int lvl, String name, Pet pet, String[] friends) {
        this.online = online;
        this.lvl = lvl;
        this.nickname = name;
        this.pet = pet;
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Character{" +
                "online=" + online +
                ", lvl=" + lvl +
                ", name='" + nickname + '\'' +
                ", pet=" + pet +
                ", friends=" + Arrays.toString(friends) +
                '}';
    }
}
