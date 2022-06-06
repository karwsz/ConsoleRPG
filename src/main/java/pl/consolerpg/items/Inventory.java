package pl.consolerpg.items;

import java.util.*;

public class Inventory {

    private final List<Item> backpack;
    private final Map<Item.Type, Item> equippedItems;

    public Inventory() {
        this.backpack = new ArrayList<>();
        this.equippedItems = new HashMap<>();
    }

    public Item getEquippedItem(Item.Type slot) {
        return equippedItems.get(slot);
    }

    public void equip(Item item) {
        backpack.remove(item);
        Item equipped = equippedItems.get(item.getType());
        if (equipped != null) {
            backpack.add(equipped);
        }
        equippedItems.put(item.getType(), item);
    }

    public void equipAll(Collection<Item> item) {
        item.forEach(this::equip);
    }

    public Map<Item.Type, Item> getEquippedItems() {
        return equippedItems;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                ", mainHand=" + equippedItems.get(Item.Type.WEAPON) +
                ", offhand=" + equippedItems.get(Item.Type.OFFHAND) +
                ", armor=" + equippedItems.get(Item.Type.OFFHAND) +
                '}';
    }
}
