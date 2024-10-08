package environment;

import base.Vertex2;

import java.util.ArrayList;

public class SplitResult {

    protected Polygon polygon;
    protected Polyline polyline;
    protected EdgeLine edgeLine;
    protected SimpleLine simpleLine;

    public SplitResult(ArrayList<Vertex2> points) {
        simpleLine = new SimpleLine(points);
    }

    public SplitResult(ArrayList<Vertex2> points, int[] polygonIndex, int[] polylineIndex, int[] edgeLineIndex) {
        polygon = new Polygon(points, polygonIndex);
        polyline = new Polyline(points, polylineIndex);
        edgeLine = new EdgeLine(points, edgeLineIndex);
    }

    protected static class Polygon {
        protected int[] xPoints, yPoints;
        protected int nPoints;

        protected Polygon(ArrayList<Vertex2> points, int[] polygonIndex) {
            nPoints = polygonIndex.length;

            xPoints = new int[nPoints];
            yPoints = new int[nPoints];

            for (int index = 0; index < nPoints; index++) {
                xPoints[index] = (int) points.get(polygonIndex[index]).x;
                yPoints[index] = (int) points.get(polygonIndex[index]).y;
            }
        }
    }

    protected static class Polyline {
        protected int[] xPoints, yPoints;
        protected int nPoints;

        protected Polyline(ArrayList<Vertex2> points, int[] polylineIndex) {
            nPoints = polylineIndex.length;

            xPoints = new int[nPoints];
            yPoints = new int[nPoints];

            for (int index = 0; index < nPoints; index++) {
                xPoints[index] = (int) points.get(polylineIndex[index]).x;
                yPoints[index] = (int) points.get(polylineIndex[index]).y;
            }
        }
    }

    protected static class EdgeLine {
        protected int[] xPoints, yPoints;
        protected int nPoints;

        protected EdgeLine(ArrayList<Vertex2> points, int[] edgeLineIndex) {
            nPoints = edgeLineIndex.length;

            xPoints = new int[nPoints];
            yPoints = new int[nPoints];

            for (int index = 0; index < nPoints; index++) {
                xPoints[index] = (int) points.get(edgeLineIndex[index]).x;
                yPoints[index] = (int) points.get(edgeLineIndex[index]).y;
            }
        }
    }

    protected static class SimpleLine {
        protected int[] xPoints, yPoints;
        protected int nPoints;

        protected SimpleLine(ArrayList<Vertex2> points) {
            nPoints = points.size();

            xPoints = new int[nPoints];
            yPoints = new int[nPoints];

            for (int index = 0; index < nPoints; index++) {
                xPoints[index] = (int) points.get(index).x;
                yPoints[index] = (int) points.get(index).y;
            }
        }
    }
}
