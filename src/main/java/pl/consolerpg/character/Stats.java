package pl.consolerpg.character;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Stats {

    public Stats() {
        set(NamedStats.VITALITY, 2);
    }

    private final HashMap<String, Integer> data = new HashMap<>();

    public int get(String stat) {
        return data.getOrDefault(stat, 0);
    }

    public void set(String stat, int value) {
        data.put(stat, value);
    }

    public void add(String stat, int addition) {
        set(stat, get(stat) + addition);
    }

    public int get(NamedStats namedStat) {
        return data.getOrDefault(namedStat.name(), 0);
    }

    public void set(NamedStats namedStat, int value) {
        data.put(namedStat.name(), value);
    }

    public void add(NamedStats namedStat, int addition) {
        set(namedStat.name(), get(namedStat) + addition);
    }


    public HashMap<String, Integer> getData() {
        return data;
    }

    public static Stats randomStats(int limit, int limitPerStat) {
        Stats stats = new Stats();
        for (NamedStats nS : NamedStats.values()) {
            if (limit <= 0) break;
            int val = ThreadLocalRandom.current().nextInt(Math.min(limit + 1, limitPerStat + 1));
            stats.set(nS, val);
            limit -= val;
        }

        if (stats.get(NamedStats.VITALITY) < 1) stats.set(NamedStats.VITALITY, 1);
        if (stats.get(NamedStats.STRENGTH) < 1) stats.set(NamedStats.VITALITY, 1);

        return stats;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "data=" + data +
                '}';
    }
}
