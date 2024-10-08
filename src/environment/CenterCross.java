package environment;

import base.Matrix3;
import base.Projection;
import base.Vertex2;
import base.Vertex3;

import java.awt.*;
import java.util.ArrayList;

public class CenterCross {

    private ArrayList<Vertex3> vertices;

    public CenterCross() {
        setupVertices();
    }

    public void draw(Graphics2D graphics2D, Projection projection, Matrix3 matrix) {

        ArrayList<Vertex2> points = new ArrayList<>();

        for (Vertex3 vertex : vertices) {
            Vertex3 v = matrix.transform(vertex);
            Vertex2 p = projection.perform(v);

            points.add(p);
        }

        graphics2D.setColor(Color.RED);
        graphics2D.drawLine((int) points.get(0).x, (int) points.get(0).y, (int) points.get(1).x, (int) points.get(1).y);

        graphics2D.setColor(Color.GREEN);
        graphics2D.drawLine((int) points.get(2).x, (int) points.get(2).y, (int) points.get(3).x, (int) points.get(3).y);

        graphics2D.setColor(Color.YELLOW);
        graphics2D.drawLine((int) points.get(4).x, (int) points.get(4).y, (int) points.get(5).x, (int) points.get(5).y);
    }

    private void setupVertices() {
        vertices = new ArrayList<>();

        vertices.add(new Vertex3(-20, 0, 0));
        vertices.add(new Vertex3(20, 0, 0));

        vertices.add(new Vertex3(0, -20, 0));
        vertices.add(new Vertex3(0, 20, 0));

        vertices.add(new Vertex3(0, 0, -20));
        vertices.add(new Vertex3(0, 0, 20));
    }

}
