package pl.consolerpg.userinterface;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.*;

public class UserInterface extends JFrame {

    private static final int KEY_UP = 38;
    private static final int KEY_DOWN = 40;
    private static final int KEY_LEFT = 37;
    private static final int KEY_RIGHT = 39;

    private String[][] map = new String[18][50];
    private int playerX = 9;
    private int playerY = 9;

    public UserInterface() {
        super("Karol's Console RPG game");

        JTextPane testPane = new JTextPane();
        testPane.setBackground(Color.BLACK);
        testPane.setForeground(Color.WHITE);
        testPane.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
        testPane.setEditable(false);


        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 1;

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        add(new JScrollPane(testPane), constraints);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setFocusable(true);
        setLocationRelativeTo(null);

        generateMap();
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                // Do nothing
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                int keyPressed = keyEvent.getKeyCode();
                map[playerX][playerY] = " ";
                switch (keyPressed) {
                    case KEY_UP:
                        playerX -= 1;
                        break;
                    case KEY_DOWN:
                        playerX += 1;
                        break;
                    case KEY_LEFT:
                        playerY -= 1;
                        break;
                    case KEY_RIGHT:
                        playerY += 1;
                        break;
                }
                testPane.setText("");
                map[playerX][playerY] = "O";
                testPane.setText(mapToString());
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                // Do nothing
            }
        });

        testPane.setText(mapToString());
        requestFocusInWindow();
    }

    private String task() {
        String nazwa =  "==================================================\n" +
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
                         "==================================================";

        String postacIbron =  "==================================================\n" +
                              "|                                                |\n" +
                              "|    CHOOSE YOUR CLASS                           |\n" +
                              "|                                                |\n" +
                              "|    WARRIOR  <-               STR=2             |\n" +
                              "|    MAGE                      DEX=9             |\n" +
                              "|    ROGUE                     VIT=1             |\n" +
                              "|                              ENE=5             |\n" +
                              "|                                                |\n" +
                              "|                                                |\n" +
                              "|                                                |\n" +
                              "|       tak samo strzalka ruszasz enter wybierasz|\n" +
                              "|                                                |\n" +
                              "|                                                |\n" +
                              "|         to samo dla broni                      |\n" +
                              "|                                                |\n" +
                              "|                                                |\n" +
                              "==================================================";



        return "==================================================\n" +
                "|HP 100/100   | DMG=99 DEF=99 VIT=99 |    ENEMY  |\n" +
                "||*****-----| | STR=99 DEX=99 ENE=99 | HP 100/100|\n" +
                "|================================================|\n" +
                "|                                                |\n" +
                "|                                                |\n" +
                "|                                                |\n" +
                "|                                                |\n" +
                "|                                                |\n" +
                "|                       O                        |\n" +
                "|                                                |\n" +
                "|                                                |\n" +
                "|                                                |\n" +
                "|                                                |\n" +
                "|                                                |\n" +
                "|                                                |\n" +
                "|                                                |\n" +
                "==================================================";

    }

    private String mapToString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                output.append(map[i][j]);
            }
            output.append("\n");
        }
        return output.toString();
    }

    private void generateMap() {
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], " ");
        }

        for (int i = 0; i < map.length; i++) {
            map[i][0] = "X";
            map[i][10] = "X";
            map[i][20] = "X";
            map[i][30] = "X";
            map[i][40] = "X";
            map[i][map[0].length - 1] = "X";
            map[i][map[0].length - 1] = "X";
        }

        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = "X";
            map[4][i] = "X";
            map[8][i] = "X";
            map[12][i] = "X";
            map[map.length - 1][i] = "X";
        }
    }

}