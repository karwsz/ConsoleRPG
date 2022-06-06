package pl.consolerpg.items;

import pl.consolerpg.character.NamedStats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemCreator {

    private static final HashMap<String, Item> instances = new HashMap<>();


    public static void initialize() {
        registerItem("dagger", new ItemCreator("Sztylet", Item.Type.WEAPON).modify(NamedStats.STRENGTH, 1).create());
        registerItem("long_sword", new ItemCreator("Długi miecz", Item.Type.WEAPON).modify(NamedStats.STRENGTH, 2).modify(NamedStats.DEXTERITY, -1).create());
        registerItem("l_chainmail", new ItemCreator("Lekka kolczuga", Item.Type.ARMOR).modify(NamedStats.DEXTERITY, 1).create());
        registerItem("h_iron_armor", new ItemCreator("Ciężka żelazna zbroja", Item.Type.ARMOR).modify(NamedStats.STRENGTH, -1).modify(NamedStats.VITALITY, 2).create());
    }

    public static void registerItem(String id, Item item) {
        instances.put(id, item);
    }

    public static Item getItemByID(String id) {
        return instances.get(id);
    }

    public static Item getItemByName(String name) {
        for (Item item : instances.values()) {
            if (item.getName().equalsIgnoreCase(name)) return item;
        }
        throw new IllegalArgumentException();
    }

    private final Item item;

    public ItemCreator(String name, Item.Type type) {
        item = new Item(name, type);
    }

    public ItemCreator modify(String stat, Integer value) {
        item.getStatModifier().put(stat, value);
        return this;
    }
    public ItemCreator modify(NamedStats stat, Integer value) {
        item.getStatModifier().put(stat.name(), value);
        return this;
    }

    public Item create() {
        return item;
    }

    public static List<Item> getByType(Item.Type type) {
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : instances.values()) {
            if (item.getType().equals(type)) items.add(item);
        }
        return items;
    }


    public static String displayList(Item.Type type) {
        StringBuilder builder = new StringBuilder();
        for (Item c : getByType(type)) {
            builder.append(c.getName()).append(", ");
        }
        String built = builder.toString();
        return built.substring(0, built.length() - 2);
    }


}
