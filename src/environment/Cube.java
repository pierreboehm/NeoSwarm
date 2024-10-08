package environment;

import base.Matrix3;
import base.Projection;
import base.Vertex2;
import base.Vertex3;

import java.awt.*;
import java.util.ArrayList;

public class Cube {
    private static final int[] AP = {0, 1, 2, 6, 5, 4, 0};  // 90 .. 180
    private static final int[] BP = {1, 2, 3, 7, 6, 5, 1};  // 0 .. 90
    private static final int[] CP = {2, 3, 0, 4, 7, 6, 2};  // 0 .. -90
    private static final int[] DP = {3, 0, 1, 5, 4, 7, 3};  // -90 .. -180

    private static final int[] AL = {2, 3, 0};
    private static final int[] BL = {3, 0, 1};
    private static final int[] CL = {0, 1, 2};
    private static final int[] DL = {1, 2, 3};

    private static final int[] AE = {1, 5};
    private static final int[] BE = {2, 6};
    private static final int[] CE = {3, 7};
    private static final int[] DE = {0, 4};

    private ArrayList<Vertex3> vertices;

    public Cube(int length, int width, int height) {
        setupVertices(length, width, height);
    }

    public void draw(Graphics2D graphics2D, Projection projection, Matrix3 matrix) {

        ArrayList<Vertex2> points = new ArrayList<>();

        for (Vertex3 vertex : vertices) {
            Vertex3 v = matrix.transform(vertex);
            Vertex2 p = projection.perform(v);

            points.add(p);
        }

        SplitResult result = perpareSplitResult(points, projection.yRotation);

        graphics2D.setColor(Color.WHITE);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f));

        graphics2D.drawPolygon(result.polygon.xPoints, result.polygon.yPoints, result.polygon.nPoints);
        graphics2D.drawPolyline(result.polyline.xPoints, result.polyline.yPoints, result.polyline.nPoints);
        graphics2D.drawLine(result.edgeLine.xPoints[0], result.edgeLine.yPoints[0], result.edgeLine.xPoints[1], result.edgeLine.yPoints[1]);

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    private SplitResult perpareSplitResult(ArrayList<Vertex2> points, int yRotation) {
        if (yRotation <= 180 && yRotation >= 90) {
            return new SplitResult(points, AP, AL, AE);
        } else if (yRotation <= 90 && yRotation >= 0) {
            return new SplitResult(points, BP, BL, BE);
        } else if (yRotation >= -90 && yRotation <= 0) {
            return new SplitResult(points, CP, CL, CE);
        } else {
            return new SplitResult(points, DP, DL, DE);
        }
    }

    private void setupVertices(int length, int width, int height) {
        vertices = new ArrayList<>();

        double halfWidth = width / 2.0;
        double halfLength = length / 2.0;

        vertices.add(new Vertex3(halfLength, 0, halfWidth));
        vertices.add(new Vertex3(-halfLength, 0, halfWidth));
        vertices.add(new Vertex3(-halfLength, 0, -halfWidth));
        vertices.add(new Vertex3(halfLength, 0, -halfWidth));

        vertices.add(new Vertex3(halfLength, height, halfWidth));
        vertices.add(new Vertex3(-halfLength, height, halfWidth));
        vertices.add(new Vertex3(-halfLength, height, -halfWidth));
        vertices.add(new Vertex3(halfLength, height, -halfWidth));
    }
}
