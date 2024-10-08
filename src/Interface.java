import construct.ConstructView;

import javax.swing.*;
import java.awt.*;

public class Interface {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 900;

    private static JFrame frame;

    public static void setupAndRun() {
        createInterface();
        showInterface(true);
    }

    private static void createInterface() {
        frame = new JFrame("NeoSwarm");

        frame.setSize(WIDTH, HEIGHT);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);

        ConstructView constructView = new ConstructView();
        frame.add(constructView);

        frame.pack();
    }

    public static void showInterface(boolean show) {
        frame.setVisible(show);
    }
}
