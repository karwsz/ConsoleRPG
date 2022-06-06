package pl.consolerpg.combat;

import pl.consolerpg.character.NamedStats;
import pl.consolerpg.character.Character;

import java.util.concurrent.ThreadLocalRandom;

public class BattleManager {



    public static void fight(Character player, Character foe) {
        Character first = ThreadLocalRandom.current().nextBoolean() ? player : foe;
        Character second = player.equals(first) ? foe : player;
        while (player.getCurrentHealthPoints() > 0 && foe.getCurrentHealthPoints() > 0) {



            System.out.println("");
            System.out.printf("%s (%s/%s) atakuje %s (%s/%s) z siłą %s%n", first.getName(), first.getCurrentHealthPoints(), first.getMaxHealthPoints(), second.getName(), second.getCurrentHealthPoints(), second.getMaxHealthPoints(), first.getDamage());
            System.out.printf("%s blokuje %s obrażeń%n", second.getName(), second.getModifiedStat(NamedStats.DEXTERITY));
            first.attack(second);

            if (second.getCurrentHealthPoints() == 0) {
                System.out.printf("%s ginie%n", second.getName());
            }

            Character holder = second;
            second = first;
            first = holder;

        }
    }





}
