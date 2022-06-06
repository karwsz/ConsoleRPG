package pl.consolerpg.character;

import pl.consolerpg.items.Item;

import java.util.HashSet;
import java.util.List;

public class CharacterCreator {

    public static HashSet<Character> aliveCharacters = new HashSet<>();

    public static Character createCharacter(String name, CharacterClass type) {
        Character newCharacter = new Character(name, type);
        aliveCharacters.add(newCharacter);
        return newCharacter;
    }

    public static void createCharacter(String name, Stats stats, List<Item> items) {
        aliveCharacters.add(new Character(name, stats, items));
    }

    public static Character get(String name) {
        for (Character c : aliveCharacters) {
            if (c.getName().equalsIgnoreCase(name)) return c;
        }
        return null;
    }

    public static void remove(String name) {
        aliveCharacters.removeIf(c -> c.getName().equalsIgnoreCase(name));
    }

}
