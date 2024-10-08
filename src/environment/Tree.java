package environment;

import base.Matrix3;
import base.Projection;
import base.Vertex2;
import base.Vertex3;

import java.awt.*;
import java.util.ArrayList;

public class Tree {
    private ArrayList<Vertex3> vertices;
    public Vertex3 position;
    public boolean marked;

    public Tree(double x, double y, double z) {
        position = new Vertex3(x, y, z);
        setupVertices();
    }

    public void draw(Graphics2D graphics2D, Projection projection, Matrix3 matrix) {
        graphics2D.setColor(marked ? Color.RED : Color.GREEN);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f));

        ArrayList<Vertex2> points = new ArrayList<>();

        for (Vertex3 vertex : vertices) {
            Vertex3 v = matrix.transform(vertex);
            Vertex2 p = projection.perform(v);

            points.add(p);
        }

        SplitResult result = new SplitResult(points);

        //graphics2D.setStroke(new BasicStroke(4));
        graphics2D.drawPolyline(result.simpleLine.xPoints, result.simpleLine.yPoints, result.simpleLine.nPoints);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    private void setupVertices() {
        vertices = new ArrayList<>();

        vertices.add(new Vertex3(position.x, 0, position.z));
        vertices.add(new Vertex3(position.x, position.y, position.z));
    }
}
