package construct;

import javax.swing.*;
import java.awt.*;

public class ConstructView extends JPanel {

    private ConstructPanel constructPanel;

    public ConstructView() {
        super();

        setBackground(Color.LIGHT_GRAY);
        initView();

        // TODO: setup key-listener for drone development (manual controls)
    }

    private void initView() {
        setLayout(new BorderLayout());

        JSlider scalingSlider = new JSlider(SwingConstants.VERTICAL,1, 200, 80);
        scalingSlider.setToolTipText("Up/Down - Zoom In/Out");
        add(scalingSlider, BorderLayout.WEST);

        JSlider verticalSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, -5);
        verticalSlider.setToolTipText("Up/Down - Turn Backwards/Towards");
        add(verticalSlider, BorderLayout.EAST);

        JSlider horizontalSlider = new JSlider(-180, 180, 30);
        horizontalSlider.setToolTipText("Left/Right - Turn Left/Right");
        add(horizontalSlider, BorderLayout.SOUTH);

        constructPanel = new ConstructPanel(-5, 30, 80);
        add(constructPanel, BorderLayout.CENTER);

        scalingSlider.addChangeListener(e -> {
            constructPanel.setScaling(scalingSlider.getValue());
            constructPanel.repaint();
        });

        verticalSlider.addChangeListener(e -> {
            constructPanel.setXRotation(verticalSlider.getValue());
            constructPanel.repaint();
        });

        horizontalSlider.addChangeListener(e -> {
            constructPanel.setYRotation(horizontalSlider.getValue());
            constructPanel.repaint();
        });
    }

}
