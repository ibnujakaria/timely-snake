package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * Created by ibnujakaria on 17/11/16.
 */
public class Snake extends Group {

    private int length, step = 10;
    private Atom atoms[];
    private Scene scene;
    private String direction = "left";

    Snake(Scene scene, int length) {
        this.length = length;
        atoms = new Atom[1000];

        this.scene = scene;
        prepareSnakes();
    }

    private void prepareSnakes() {
        for (int i = 0; i < length; i++) {
            double x = scene.getWidth()/2 + (i * step) / 10 * 10;
            double y = scene.getHeight()/2 / 10 * 10;
            atoms[i] = new Atom(x, y);

            if (i == 0) {
                atoms[i].setHead(true);
            }

            getChildren().add(atoms[i]);
        }
    }

    public Atom getHead () {
        return atoms[0];
    }

    public void moving () {
        for (int i = length - 1; i > 0; i--) {
            atoms[i].setCenterX(atoms[i-1].getCenterX());
            atoms[i].setCenterY(atoms[i-1].getCenterY());
        }

        switch (direction) {
            case "right":
                getHead().setCenterX(getHead().getCenterX() + 10);
                break;
            case "left":
                getHead().setCenterX(getHead().getCenterX() - 10);
                break;
            case "up":
                getHead().setCenterY(getHead().getCenterY() - 10);
                break;
            case "down":
                getHead().setCenterY(getHead().getCenterY() + 10);
                break;
        }

        if (getHead().getCenterX() < 0) {
            getHead().setCenterX(scene.getWidth());
        }

        if (getHead().getCenterX() > scene.getWidth()) {
            getHead().setCenterX(0);
        }

        if (getHead().getCenterY() < 0) {
            getHead().setCenterY(scene.getHeight());
        }

        if (getHead().getCenterY() > scene.getHeight()) {
            getHead().setCenterY(0);
        }
    }

    public void turnRight () {
        if (!direction.equals("left")) {
            direction = "right";
        }
    }

    public void turnLeft () {
        if (!direction.equals("right")) {
            direction = "left";
        }
    }

    public void turnUp () {
        if (!direction.equals("bottom")) {
            direction = "up";
        }
    }

    public void eat() {
        atoms[length] = new Atom(-100, -100);
        getChildren().add(atoms[length]);
        length++;

        System.out.println(length);
    }

    public void turnDown() {
        if (!direction.equals("up")) {
            direction = "down";
        }
    }

    public int getLength () {
        return length;
    }

    public void setLength (int length) {
        this.length = length;

        getChildren().clear();

        for (int i = 0; i < length; i++) {
            getChildren().add(atoms[i]);
        }
    }

    public Atom[] getAtoms () {
        return atoms;
    }
}
