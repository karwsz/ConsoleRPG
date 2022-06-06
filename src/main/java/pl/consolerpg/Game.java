package pl.consolerpg;

import pl.consolerpg.character.Character;
import pl.consolerpg.character.CharacterClass;
import pl.consolerpg.character.CharacterCreator;
import pl.consolerpg.character.Stats;
import pl.consolerpg.combat.BattleManager;
import pl.consolerpg.items.Item;
import pl.consolerpg.items.ItemCreator;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    Character playerCharacter;

    private Scanner scanner;

    public void startGame() {
        ItemCreator.initialize();
        CharacterClass.initialize();

        scanner = new Scanner(System.in);

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("X                                                  X");
        System.out.println("X                                                  X");
        System.out.println("X                                                  X");
        System.out.println("X    O       O       O         O       O       O   X");
        System.out.println("X                                                  X");
        System.out.println("X                                                  X");
        System.out.println("X                                                  X");
        System.out.println("X                                                  X");
        System.out.println("X                                                  X");
        System.out.println("X                     O                            X");
        System.out.println("X                                                  X");
        System.out.println("X                                                  X");
        System.out.println("X                                                  X");
        System.out.println("X                                                  X");
        System.out.println("X                                                  X");
        System.out.println("X                                                  X");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("Podaj nazwe swojej postaci: ");
        String playerCharacterName = scanner.nextLine();
        System.out.printf("Klasa postaci (%s):%n", CharacterClass.displayList());
        CharacterClass playerClass = null;
        while (playerClass == null) {
            try {
                playerClass = CharacterClass.getByDisplayName(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Wybierz jedna z dostepnych klas: " + CharacterClass.displayList());
            }
        }
        System.out.printf("Startowa bron postaci (%s):%n", ItemCreator.displayList(Item.Type.WEAPON));
        Item weapon = null;
        while (weapon == null) {
            try {
                weapon = ItemCreator.getItemByName(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Wybierz jedna z dostepnych broni: " + ItemCreator.displayList(Item.Type.WEAPON));
            }
        }

        playerCharacter = CharacterCreator.createCharacter(playerCharacterName, playerClass);
        playerCharacter.getInventory().equip(weapon);

        System.out.println("Statystyki postaci: ");
        playerCharacter.printModifiedStats();

        Character foe = findRandomFoe();

        System.out.println(foe);


        BattleManager.fight(playerCharacter, foe);

    }

    public Character findRandomFoe() {
        Stats s = Stats.randomStats(4, 2);
        Character foe = new Character("Przeciwnik", s);

        if (ThreadLocalRandom.current().nextBoolean()) {
            List<Item> weapons = ItemCreator.getByType(Item.Type.WEAPON);
            foe.getInventory().equip(weapons.get(ThreadLocalRandom.current().nextInt(weapons.size())));
        }

        if (ThreadLocalRandom.current().nextBoolean()) {
            List<Item> weapons = ItemCreator.getByType(Item.Type.ARMOR);
            foe.getInventory().equip(weapons.get(ThreadLocalRandom.current().nextInt(weapons.size())));
        }

        return foe;
    }
}
