import javax.swing.*;

public class Main {

    public static void main(String[] argv) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Interface.setupAndRun();
            }
        });
    }

}
