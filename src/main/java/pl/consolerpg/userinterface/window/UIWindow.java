package pl.consolerpg.userinterface.window;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class UIWindow {

    private static final int TAB = 9;

    public abstract String[][] display();

    protected int selectedSection = 0;

    public void onKeyPress(KeyEvent event) {
        if (event.getKeyCode() == TAB) {
            Section previous = getSections().get(0);
            selectedSection++;
            if (selectedSection > getSections().size() - 1) {
                selectedSection = 0;
            }
            previous.deselect();
            getSections().get(selectedSection).select();
        }
    }

    public abstract ArrayList<Section> getSections();

    public static abstract class Section {
        public abstract ArrayList<InteractionIndex> indexes();
        public abstract void select();
        public abstract void deselect();
    }

    public static abstract class InteractionIndex {
        private final int index;
        public InteractionIndex(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public abstract String onKeyPress(KeyEvent event);


    }

}
