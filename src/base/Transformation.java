package base;

public class Transformation {

    public static Matrix3 perform(int xRotation, int yRotation) {
        double horizontal = Math.toRadians(yRotation);
        Matrix3 horizontalTransform = new Matrix3(new double[] {
                Math.cos(horizontal), 0, Math.sin(horizontal),
                0, 1, 0,
                -Math.sin(horizontal), 0, Math.cos(horizontal)
        });

        double vertical = Math.toRadians(xRotation);
        Matrix3 verticalTransform = new Matrix3(new double[] {
                1, 0, 0,
                0, Math.cos(vertical), -Math.sin(vertical),
                0, Math.sin(vertical), Math.cos(vertical)
        });

        return horizontalTransform.multiply(verticalTransform);
    }

}
