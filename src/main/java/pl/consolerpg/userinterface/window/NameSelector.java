package pl.consolerpg.userinterface.window;

import pl.consolerpg.userinterface.UIUtils;

import java.util.ArrayList;

public class NameSelector extends UIWindow {

    private ArrayList<Section> sections = new ArrayList<>();

    private final String[][] display;

    public NameSelector() {

        display = initialDisplay();

        sections.add(new Section() {



            private final ArrayList<InteractionIndex> indexes = new ArrayList<>();

            @Override
            public ArrayList<InteractionIndex> indexes() {
                return indexes;
            }

            @Override
            public void select() {
                StringBuilder builder = new StringBuilder();
            }

            @Override
            public void deselect() {

            }
        });
    }


    private static String [][]initialDisplay() {
        return UIUtils.stringToTable(
                        "==================================================\n" +
                        "|                                                |\n" +
                        "|  YOUR NAME =  NAZWA____                        |\n" +
                        "|                                                |\n" +
                        "| ABCDEFGHIJKLMNOPQRS i tak dalej                |\n" +
                        "|    |                                           |\n" +
                        "|                                                |\n" +
                        "|  jakiś kursor pod literkami strzalkami porusasz|\n" +
                        "|  enterem wybierasz                             |\n" +
                        "|                                                |\n" +
                        "|                                                |\n" +
                        "|                                                |\n" +
                        "|                                                |\n" +
                        "|                                                |\n" +
                        "|                                                |\n" +
                        "|                                                |\n" +
                        "|                                                |\n" +
                        "==================================================");
    }

    @Override
    public String[][] display() {
        return display;
    }



    @Override
    public ArrayList<Section> getSections() {
        return sections;
    }
}
