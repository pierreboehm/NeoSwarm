package construct;

import environment.Environment;

import javax.swing.*;
import java.awt.*;

public class ConstructPanel extends JPanel {

    private static final double SCALE = 0.01;
    private int xRotation, yRotation, scaling;
    private Environment environment;

    public ConstructPanel(int xRotation, int yRotation, int scaling) {
        this.xRotation = xRotation + 180;
        this.yRotation = yRotation;
        this.scaling = scaling;

        setBackground(Color.decode("#313131"));
        setFont(new Font("Arial Narrow", Font.PLAIN, 16));
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.LIGHT_GRAY);
        graphics2D.drawString("yRotation: " + yRotation, 20, 30);
        graphics2D.drawString("  Scaling: " + scaling, 20, 50);

        drawEnvironment(graphics2D);

        graphics2D.dispose();
    }

    private void drawEnvironment(Graphics2D graphics2D) {
        graphics2D.scale(SCALE, SCALE);

        if (environment == null) {
            environment = new Environment(getWidth(), getHeight(), SCALE);
        }

        environment.draw(graphics2D, xRotation, yRotation, scaling);
    }

    public void setScaling(int scaling) {
        this.scaling = scaling;
    }

    public void setXRotation(int xRotation) {
        this.xRotation = xRotation + 180;
    }

    public void setYRotation(int yRotation) {
        this.yRotation = yRotation;
    }
}
