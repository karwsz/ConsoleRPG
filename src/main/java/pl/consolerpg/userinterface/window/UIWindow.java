package pl.consolerpg.userinterface.window;


import pl.consolerpg.userinterface.UserInterface;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class UIWindow {

    private static final int TAB = 9;

    public abstract String[][] display();

    protected int selectedSection = 0;

    public void onKeyPress(KeyEvent event) {
        if (event.getKeyCode() == TAB) {
            nextSection();
        }
        else getSections().get(selectedSection).onKeyPress(event);
    }

    public void nextSection() {
        Section previous = getSections().get(selectedSection);
        selectedSection++;
        if (selectedSection > getSections().size() - 1) {
            selectedSection = 0;
        }
        previous.deselect();
        getSections().get(selectedSection).select();
    }

    public void selectSection(int index) {
        if (index >= getSections().size()) {
            return;
        }
        getSections().get(selectedSection).deselect();
        selectedSection = index;
        getSections().get(selectedSection).select();
    }

    public void onKeyRelease(KeyEvent event) {
        getSections().get(selectedSection).onKeyRelease(event);
    }

    public abstract ArrayList<Section> getSections();

    public static abstract class Section {
        public abstract void select();
        public abstract void deselect();
        public abstract void onKeyPress(KeyEvent event);
        public void onKeyRelease(KeyEvent event) {}
    }


}
