package base;

public class Projection {

    private final double centerX, centerY;
    private final double distortion;
    public double scaleFactor;
    public int yRotation;

    public Projection(int canvasWidth, int canvasHeight, double scaleFactor) {
        this.scaleFactor = scaleFactor;
        distortion = 500d * (1.0 / scaleFactor);
        centerX = canvasWidth / (2.0 * scaleFactor);
        centerY = canvasHeight / (1.5 * scaleFactor);
    }

    public Vertex2 perform(Vertex3 vertex) {
        double x = centerX + (distortion * vertex.x * scaleFactor) / (vertex.z * scaleFactor + distortion);
        double y = centerY + (distortion * vertex.y * scaleFactor) / (vertex.z * scaleFactor + distortion);
        return new Vertex2(x, y);
    }
}
