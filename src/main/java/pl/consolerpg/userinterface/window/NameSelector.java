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





    private static final String[] letters = new String[]{
            "qwertyuiop",
            "asdfghjkl",
            "zxcvbnm"};

    private final int xStart = 2;
    private final int yStart = 5;

    private int selectedX = xStart;
    private int selectedY = yStart;



    private void setLetter() {
        setLetter(display[selectedY - 1][selectedX]);
    }

    private void setLetter(String letter) {
        display[nameStartY][nameStartX + nameIndex + 1] = letter;
        nameIndex++;
    }

    private void removeLetter() {
        display[nameStartY][nameStartX + nameIndex] = " ";
        if (nameIndex > 0) nameIndex--;
    }


    public NameSelector() {

        display = initialDisplay();



        //Leters
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
                if (x < xStart) selectedX = getRowLength(selectedY);
                else if (!isXInsideLimit(x)) selectedX = xStart;
                else selectedX = x;
                //if (x >= xStart && isXInsideLimit(x)) selectedX = x;
                if (y >= yStart && isYInsideLimit(y)) selectedY = y;
                display[selectedY][selectedX] = "^";
            }

            private boolean isYInsideLimit(int y) {
                return y - yStart < letters.length * 2;
            }

            private boolean isXInsideLimit(int x) {
                return x - xStart < letters[getRow(selectedY)].length();
            }

            private boolean isXInsideLimit(int x, int y) {
                return x - xStart < letters[getRow(y)].length();
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
                    for (int i = 0; i < letters.length; i++) {
                        UIUtils.insertStringAtIndex(display, 2, 4 + i * 2, letters[i].toUpperCase(Locale.ROOT));
                    }
                }
                else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    int newY = selectedY + 2;
                    if (isYInsideLimit(newY)) {
                        if (!isXInsideLimit(selectedX, newY)) {
                            move(getRowLength(newY), newY);
                        }
                        else move(selectedX, newY);
                    }
                    else selectSection(selectedX > letters[0].length() / 2 ? 2 : 1);
                }
                else if (event.getKeyCode() == KeyEvent.VK_UP) {
                    if (selectedY > yStart) move(selectedX, selectedY - 2);
                }
                else if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    setLetter();
                }
            }

            @Override
            public void onKeyRelease(KeyEvent event) {
                if (event.getKeyCode() == UserInterface.SHIFT) {
                    for (int i = 0; i < letters.length; i++) {
                        UIUtils.insertStringAtIndex(display, 2, 4 + i * 2, letters[i].toLowerCase(Locale.ROOT));
                    }
                }
            }
        });


        //Space
        sections.add(new Section() {


            @Override
            public void select() {
                display[11][xStart] = "^";
            }

            @Override
            public void deselect() {
                display[11][xStart] = " ";
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


        //Backspace
        sections.add(new Section() {


            @Override
            public void select() {
                display[11][xStart + 6] = "^";
            }

            @Override
            public void deselect() {
                display[11][xStart + 6] = " ";
            }

            @Override
            public void onKeyPress(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_UP) {
                    selectedX = xStart + 6;
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


    private int getRow(int y) {
        return (y - yStart) / 2;
    }

    private int getRowLength(int y) {
        return letters[getRow(y)].length() + 1;
    }

    private static String [][]initialDisplay() {
        return UIUtils.stringToTable(
                        "==================================================\n" +
                        "|                                                |\n" +
                        "|  YOUR NAME =                                   |\n" +
                        "|                                                |\n" +
                        "| qwertyuiop                                     |\n" +
                        "|                                                |\n" +
                        "| asdfghjkl                                      |\n" +
                        "|                                                |\n" +
                        "| zxcvbnm                                        |\n" +
                        "|                                                |\n" +
                        "| Space Backspace                                |\n" +
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
