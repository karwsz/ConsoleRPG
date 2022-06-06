package pl.consolerpg;

import pl.consolerpg.userinterface.UserInterface;

public class Main {

    public static void main(String[] args) {
        new UserInterface().setVisible(true);
        new Game().startGame();
    }
}
