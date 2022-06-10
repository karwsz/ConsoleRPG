package pl.consolerpg.userinterface.window;

import pl.consolerpg.userinterface.UIUtils;
import pl.consolerpg.userinterface.UserInterface;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Locale;

public class NameSelector extends UIWindow {

    private final ArrayList<Section> sections = new ArrayList<>();

    private final String[][] display;

    private static final int nameStartX = 16;
    private static final int nameStartY = 2;

    private int nameIndex = 0;

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private final int xStart = 2;
    private final int yStart = 5;

    private final int xLimit = xStart + alphabet.length() - 1;
    private final int yLimit = yStart + 2;

    private int selectedX = xStart;
    private int selectedY = yStart;



    private void setLetter() {
        setLetter(display[selectedY - 1][selectedX]);
    }

    private void setLetter(String letter) {
        display[nameStartY][nameStartX + nameIndex] = letter;
        nameIndex++;
    }

    private void removeLetter() {
        display[nameStartY][nameStartX + nameIndex] = " ";
        if (nameIndex > 0) nameIndex--;
    }


    public NameSelector() {

        display = initialDisplay();

        sections.add(new Section() {



            @Override
            public void select() {
                move(selectedX, selectedY);
            }

            @Override
            public void deselect() {
                display[selectedY][selectedX] = " ";
            }

            private void move(int x, int y) {
                display[selectedY][selectedX] = " ";
                if (x >= xStart && x <= xLimit) selectedX = x;
                if (y >= yStart && y <= yLimit) selectedY = y;
                display[selectedY][selectedX] = "^";
            }



            @Override
            public void onKeyPress(KeyEvent event) {
                if (event.getKeyCode() == UserInterface.KEY_RIGHT) {
                    move(selectedX + 1, selectedY);
                }
                else if (event.getKeyCode() == UserInterface.KEY_LEFT) {
                    move(selectedX - 1, selectedY);
                }
                else if (event.getKeyCode() == UserInterface.SHIFT) {
                    UIUtils.insertStringAtIndex(display, 2, 4, alphabet.toUpperCase(Locale.ROOT));
                }
                else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    selectSection(selectedX > alphabet.length() / 2 ? 2 : 1);
                }
                else if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    setLetter();
                }
            }

            @Override
            public void onKeyRelease(KeyEvent event) {
                if (event.getKeyCode() == UserInterface.SHIFT) {
                    UIUtils.insertStringAtIndex(display, 2, 4, alphabet.toLowerCase(Locale.ROOT));
                }
            }
        });

        sections.add(new Section() {


            @Override
            public void select() {
                display[yStart + 2][xStart] = "^";
            }

            @Override
            public void deselect() {
                display[yStart + 2][xStart] = " ";
            }

            @Override
            public void onKeyPress(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_UP) {
                    selectSection(0);
                }
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                    selectSection(2);
                }
                else if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    setLetter(" ");
                }

            }


        });

        sections.add(new Section() {


            @Override
            public void select() {
                display[yStart + 2][xStart + 17] = "^";
            }

            @Override
            public void deselect() {
                display[yStart + 2][xStart + 17] = " ";
            }

            @Override
            public void onKeyPress(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_UP) {
                    selectedX = xStart + 17;
                    selectSection(0);
                }
                else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                    selectedX = xStart;
                    selectSection(1);
                }
                else if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    removeLetter();
                }

            }


        });



        sections.get(0).select();
    }


    private static String [][]initialDisplay() {
        return UIUtils.stringToTable(
                        "==================================================\n" +
                        "|                                                |\n" +
                        "|  YOUR NAME =                                   |\n" +
                        "|                                                |\n" +
                        "| abcdefghijklmnopqrstuvwxyz                     |\n" +
                        "|                                                |\n" +
                        "| SPACE            BACKSPACE                     |\n" +
                        "|                                                |\n" +
                        "|                                                |\n" +
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
