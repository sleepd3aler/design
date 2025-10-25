package ru.serialization.json;

import java.util.Arrays;
import org.json.JSONObject;

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

    public boolean isOnline() {
        return online;
    }

    public int getLvl() {
        return lvl;
    }

    public String getNickname() {
        return nickname;
    }

    public Pet getPet() {
        return pet;
    }

    public String[] getFriends() {
        return friends;
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

    public static void main(String[] args) {
        final Character character = new Character(
                true, 150, "DrocheSlav", new Pet("Evil Devil"), new String[]
                {"Ivan777", "LeroooyJenkins"}
        );
        JSONObject jsonCharacter = new JSONObject(character);
        String res = jsonCharacter.toString();

    }
}
