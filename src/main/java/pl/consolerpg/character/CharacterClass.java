package pl.consolerpg.character;


import java.util.HashMap;

public abstract class CharacterClass {


    public static HashMap<String, CharacterClass> availableClasses = new HashMap<>();

    public static void initialize() {
        availableClasses.put("warrior", new CharacterClass() {
            @Override
            public String getDisplayName() {
                return "wojownik";
            }

            @Override
            public Stats getDefaultStats() {
                Stats stats = new Stats();
                stats.set(NamedStats.STRENGTH, 2);
                stats.set(NamedStats.VITALITY, 2);

                return stats;
            }
        });

        availableClasses.put("mage", new CharacterClass() {
            @Override
            public String getDisplayName() {
                return "mag";
            }

            @Override
            public Stats getDefaultStats() {
                Stats stats = new Stats();
                stats.set(NamedStats.ENERGY, 2);
                stats.set(NamedStats.DEXTERITY, 1);
                stats.set(NamedStats.STRENGTH, 1);

                return stats;
            }
        });

        availableClasses.put("rogue", new CharacterClass() {
            @Override
            public String getDisplayName() {
                return "łotr";
            }

            @Override
            public Stats getDefaultStats() {
                Stats stats = new Stats();
                stats.set(NamedStats.STRENGTH, 1);
                stats.set(NamedStats.DEXTERITY, 3);

                return stats;
            }
        });

    }

    public abstract String getDisplayName();
    public abstract Stats getDefaultStats();

    public static String displayList() {
        StringBuilder builder = new StringBuilder();
        for (CharacterClass c : availableClasses.values()) {
            builder.append(c.getDisplayName()).append(", ");
        }
        String built = builder.toString();
        return built.substring(0, built.length() - 2);
    }

    public static CharacterClass getByDisplayName(String displayName) {

        for (CharacterClass c : availableClasses.values()) {
            if (c.getDisplayName().equalsIgnoreCase(displayName)) return c;
        }

        throw new IllegalArgumentException();
    }

}
