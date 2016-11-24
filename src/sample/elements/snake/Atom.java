package sample.elements.snake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by ibnujakaria on 17/11/16.
 */
public class Atom extends Circle {

    private boolean head;

    public Atom (double x, double y) {
        super(x, y, 5);

        setHead(false);
        setFill(Color.web("white", 0.5));
    }

    public Atom (double x, double y, boolean head) {
        super(x, y, 10);

        setHead(true);
        setFill(Color.web("white", 0.5));
    }

    public void setHead (boolean head) {
        this.head = head;
        if (head)
            setFill(Color.web("red", 0.5));
    }
}
