package pl.consolerpg.character;

public enum NamedStats {
    STRENGTH("Siła"), DEXTERITY("Zręczność"), VITALITY("Witalność"), ENERGY("Energia");


    NamedStats(String displayName) {
        this.displayName = displayName;
    }

    private final String displayName;

    public String getDisplayName() {
        return displayName;
    }
}
