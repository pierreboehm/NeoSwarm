package environment;

import base.*;
import drone.Drone;
import drone.Sensor;
import util.Util;

import java.awt.*;
import java.util.ArrayList;

public class Environment {
    private static final int CUBE_LENGTH = 800;
    private static final int CUBE_WIDTH = 600;
    private static final int CUBE_HEIGHT = 200;

    private final Projection projection;
    private Cube cube;
    private final CenterCross cross;
    private final ArrayList<Tree> trees = new ArrayList<>();
    private final ArrayList<Drone> drones = new ArrayList<>();

    public Environment(int canvasWidth, int canvasHeight, double scale) {
        cross = new CenterCross();
        projection = new Projection(canvasWidth, canvasHeight, scale);

        setupBounds();
        setupTrees();
        setupDrones(scale);
    }

    public void draw(Graphics2D graphics2D, int xRotation, int yRotation, int scaling) {
        projection.scaleFactor = scaling;
        projection.yRotation = yRotation;

        updateDrones();
        Matrix3 transform = Transformation.perform(xRotation, yRotation);

        drawCross(graphics2D, transform);
        drawCube(graphics2D, transform);
        drawTrees(graphics2D, transform);
        drawDrones(graphics2D, transform);
    }

    private void drawCross(Graphics2D graphics2D, Matrix3 matrix) {
        cross.draw(graphics2D, projection, matrix);
    }

    private void drawCube(Graphics2D graphics2D, Matrix3 matrix) {
        cube.draw(graphics2D, projection, matrix);
    }

    private void drawTrees(Graphics2D graphics2D, Matrix3 matrix) {
        for (Tree tree : trees) {
            tree.draw(graphics2D, projection, matrix);
        }
    }

    private void drawDrones(Graphics2D graphics2D, Matrix3 matrix) {
        for (Drone drone : drones) {
            drone.draw(graphics2D, projection, matrix);
        }
    }

    private void setupDrones(double scale) {
        double innerLengthMin = CUBE_LENGTH / 2.0 - 11.0;
        double innerWidthMin = CUBE_WIDTH / 2.0 - 11.0;

        double innerLengthMax = CUBE_LENGTH / 2.0 - 4.0;
        double innerWidthMax = CUBE_WIDTH / 2.0 - 4.0;

        drones.add(new Drone(
                Util.randomValue(innerLengthMin, innerLengthMax),
                scale,
                Util.randomValue(innerWidthMin, innerWidthMax)));
    }

    private void setupTrees() {
        double halfLength = CUBE_LENGTH / 2.0 - 10.0;
        double halfWidth = CUBE_WIDTH / 2.0 - 10.0;
        double halfHeight = CUBE_HEIGHT / 2.0;

        for (int i = 0; i < 240; i++) {
            trees.add(new Tree(
                    Util.randomValue(-halfLength, halfLength),
                    Util.randomValue(10.0, halfHeight),
                    Util.randomValue(-halfWidth, halfWidth))
            );
        }
    }

    private void setupBounds() {
        cube = new Cube(CUBE_LENGTH, CUBE_WIDTH, CUBE_HEIGHT);
    }

    private void updateDrones() {
        for (Drone drone : drones) {
            drone.update(findNearByTrees(drone));
        }
    }

    private ArrayList<Vertex3> findNearByTrees(Drone drone) {
        ArrayList<Vertex3> resultList = new ArrayList<>();

        for (Tree tree : trees) {
            tree.marked = isObstacle(tree, drone);
            if (tree.marked) {
                resultList.add(tree.position);
            }
        }

        return resultList;
    }

    private boolean isObstacle(Tree tree, Drone drone) {
        double length = Util.getDistance(tree.position, drone.position);
        return length < Sensor.TRIGGER_WIDTH;
    }
}
