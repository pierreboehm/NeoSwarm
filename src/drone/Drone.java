package drone;

import base.Matrix3;
import base.Projection;
import base.Vertex2;
import base.Vertex3;

import java.awt.*;
import java.util.ArrayList;

public class Drone {

    private ArrayList<Vertex3> vertices;
    public Vertex3 position;
    private int scale = 0;
    private int alpha = 1;

    public Drone(double x, double y, double z) {
        position = new Vertex3(x, y, z);
        scale = (int) y;
        setupVertices();
    }

    public void update(ArrayList<Vertex3> obstacles) {
        // TODO: 1) filter the nearest obstacle on current way
        //       2) update movement
    }

    public void draw(Graphics2D graphics2D, Projection projection, Matrix3 matrix) {
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .6f));

        ArrayList<Vertex2> points = new ArrayList<>();

        for (Vertex3 vertex : vertices) {
            Vertex3 v = matrix.transform(vertex);
            Vertex2 p = projection.perform(v);

            points.add(p);
        }

        //scale = (int) (projection.scaleFactor * 10);
        //graphics2D.fillOval((int) points.get(0).x, (int) points.get(0).y, scale, scale/*(int) points.get(1).x, (int) points.get(1).y*/);

        graphics2D.drawLine((int) points.get(2).x, (int) points.get(2).y, (int) points.get(4).x, (int) points.get(4).y);
        graphics2D.drawLine((int) points.get(3).x, (int) points.get(3).y, (int) points.get(5).x, (int) points.get(5).y);

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    private void setupVertices() {
        vertices = new ArrayList<>();

        vertices.add(new Vertex3(position.x - 1, position.y, position.z - 1));
        vertices.add(new Vertex3(position.x + 1, position.y, position.z + 1));

        /*vertices.add(new Vertex3(position.x - 2 , position.y, position.z - 2));
        vertices.add(new Vertex3(position.x - 2 , position.y, position.z + 2));
        vertices.add(new Vertex3(position.x + 2 , position.y, position.z + 2));
        vertices.add(new Vertex3(position.x + 2 , position.y, position.z - 2));*/

        //vertices.add(new Vertex3(position.x + 1.0, scale, position.z + 1.0));
        //turnVerticesLeft();

        double phi = 0.0 / Math.PI * 180.0;
        vertices.add(new Vertex3(position.x + 2.0 * Math.sin(phi), position.y, position.z + 2.0 * Math.cos(phi)));

        phi = 45.0 / Math.PI * 180.0;
        vertices.add(new Vertex3(position.x + 2.0 * Math.sin(phi), position.y, position.z + 2.0 * Math.cos(phi)));

        phi = 135.0 / Math.PI * 180.0;
        vertices.add(new Vertex3(position.x + 2.0 * Math.sin(phi), position.y, position.z + 2.0 * Math.cos(phi)));

        phi = 225.0 / Math.PI * 180.0;
        vertices.add(new Vertex3(position.x + 2.0 * Math.sin(phi), position.y, position.z + 2.0 * Math.cos(phi)));
    }

    private void turnVerticesLeft() {
        for (int index = 2; index < vertices.size(); index++) {
            double x = vertices.get(index).x;
            double z = vertices.get(index).z;

            double phi = alpha / Math.PI * 180.0;

            double nx = x * Math.sin(phi);
            double nz = z * Math.cos(phi);

            vertices.get(index).x = nx;
            vertices.get(index).z = nz;
        }

        alpha = (alpha + 1) % 360;
    }
}
