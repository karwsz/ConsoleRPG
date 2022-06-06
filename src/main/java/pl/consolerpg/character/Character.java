package pl.consolerpg.character;

import pl.consolerpg.items.Inventory;
import pl.consolerpg.items.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Character {

    public Character(String name) {
        this.name = name;
        this.stats = new Stats();
        this.health = getMaxHealthPoints();
    }

    public Character(String name, CharacterClass type) {
        this.name = name;
        this.stats = type.getDefaultStats();
        this.health = getMaxHealthPoints();
    }

    public Character(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
        this.health = getMaxHealthPoints();
    }

    public Character(String name, Stats stats, List<Item> items) {
        this.name = name;
        this.stats = stats;
        this.health = getMaxHealthPoints();
        this.inventory.equipAll(items);
    }


    //vars

    private final String name;

    private final Stats stats;

    private final Inventory inventory = new Inventory();

    private int health;


    //methods


    public String getName() {
        return name;
    }

    public Stats getBaseStats() {
        return stats;
    }

    public int getModifiedStat(String name) {
        return stats.get(name) + getStatModifier(name);
    }

    public int getModifiedStat(NamedStats name) {
        return  getModifiedStat(name.name());
    }

    public int getStatModifier(String name) {
        return inventory.getEquippedItems().values().stream()
                .map(Item::getStatModifier)
                .filter(Objects::nonNull)
                .filter(modifier -> modifier.containsKey(name))
                .map(modifier -> modifier.get(name))
                .mapToInt(Integer::intValue)
                .sum();
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health < 0) this.health = 0;
        else if (health > getMaxHealthPoints()) this.health = getMaxHealthPoints();
    }

    public void addHealth(int health) {
        this.health += health;
        if (this.health < 0) this.health = 0;
        else if (health > getMaxHealthPoints()) this.health = getMaxHealthPoints();
    }

    public int getCurrentHealthPoints() {
        return health;
    }

    public int getMaxHealthPoints() {
        return stats.get(NamedStats.VITALITY) * 5;
    }

    public Inventory getInventory() {
        return inventory;
    }

    //combat


    public int getDamage() {
        return getModifiedStat(NamedStats.STRENGTH) * 2;
    }





    public int getDefence() {
        return getModifiedStat(NamedStats.DEXTERITY);
    }

    public void attack(Character target) {
        int damage = getDamage();
        int defence = target.getDefence();
        if (defence < 0) defence = 0;
        int finalDamage = damage > defence ? damage - defence : 0;
        target.addHealth(-finalDamage);
    }

    public void printModifiedStats() {
        HashMap<String, Integer> data = (HashMap<String, Integer>) stats.getData().clone();
        inventory.getEquippedItems().values().forEach(item -> mergeStats(data, item));
        for (NamedStats nS : NamedStats.values()) {
            data.putIfAbsent(nS.name(), 0);
        }
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            builder.append(NamedStats.valueOf(entry.getKey()).getDisplayName()).append(": ").append(entry.getValue()).append("\n");
        }
        System.out.println(builder);
    }

    private void mergeStats(HashMap<String, Integer> stats, Item item) {
        if (item == null) return;
        for (Map.Entry<String, Integer> entry : item.getStatModifier().entrySet()) {
            if (stats.containsKey(entry.getKey())) {
                int value = stats.get(entry.getKey());
                stats.put(entry.getKey(), value + entry.getValue());
            }
            else stats.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", stats=" + stats +
                ", inventory=" + inventory +
                ", health=" + health +
                '}';
    }
}
