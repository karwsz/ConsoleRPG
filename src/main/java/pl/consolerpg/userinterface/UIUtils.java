package pl.consolerpg.userinterface;

public class UIUtils {
    public static String tableToString(String[][] table) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                output.append(table[i][j]);
            }
            output.append("\n");
        }
        return output.toString();
    }

    public static String[][] stringToTable(String string) {
        String[] split = string.split("\n");
        String[][] result = new String[split.length][split[0].length()];

        for (int i = 0; i < split.length; i++) {
            for (int j = 0; j < split[i].length(); j++) {
                result[i][j] = String.valueOf(split[i].charAt(j));
            }
        }

        return result;
    }

    public static String[][] setStringAtIndex(String string, int y, int x, String value) {
        String[][] table = stringToTable(string);
        table[y][x] = value;
        return table;
    }

    public static void insertStringAtIndex(String[][] string, int x, int y, String value) {
        for (int i = 0; i < value.length(); i++) {
            string[y][x + i] = String.valueOf(value.charAt(i));
        }
    }
}
