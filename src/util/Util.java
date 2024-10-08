package util;

import base.Vertex3;

public class Util {

    public static double[] createRandomArray(int size, double lowerBound, double upperBound) {
        if (size < 1) {
            return null;
        }

        double[] result = new double[size];

        for (int i = 0; i < size; i++) {
            result[i] = randomValue(lowerBound, upperBound);
        }

        return result;
    }

    public static double[][] createRandomArray(int sizeX, int sizeY, double lowerBound, double upperBound) {
        if (sizeX < 1 || sizeY < 1) {
            return null;
        }

        double[][] result = new double[sizeX][sizeY];

        for (int i = 0; i < sizeX; i++) {
            result[i] = createRandomArray(sizeY, lowerBound, upperBound);
        }

        return result;
    }

    public static double randomValue(double lowerBound, double upperBound) {
        return Math.random() * (upperBound - lowerBound) + lowerBound;
    }

    public static double getDistance(Vertex3 position2, Vertex3 position1) {
        return Math.sqrt(((position2.z - position1.z) * (position2.z - position1.z)) +
                ((position2.x - position1.x) * (position2.x - position1.x)));
    }
}
