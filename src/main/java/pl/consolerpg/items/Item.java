package pl.consolerpg.items;

import java.util.HashMap;

public class Item {

    public enum Type {
        WEAPON, ARMOR, OFFHAND;
    }

    private final String name;
    private final HashMap<String, Integer> statModifier;
    private final Type type;

    public Item(String name, Type type) {
        this.name = name;
        this.type = type;
        this.statModifier = new HashMap<>();
    }

    public Item(String name, Type type, HashMap<String, Integer> statModifier) {
        this.name = name;
        this.statModifier = statModifier;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public HashMap<String, Integer> getStatModifier() {
        return statModifier;
    }


    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", statModifier=" + statModifier +
                ", type=" + type +
                '}';
    }
}
