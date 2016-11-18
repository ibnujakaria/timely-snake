package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Random;

/**
 * Created by ibnujakaria on 17/11/16.
 */
public class BigBuble extends Group {

    private Scene myScene;
    private Circle circles[];
    private Color colors[];
    private Timeline timeline;
    private Random rand;

    public BigBuble (Scene scene) {
        myScene = scene;
        colors = new Color[] {
                Color.web("blue", 0.1), Color.web("red", 0.1), Color.web("green", 0.1),
                Color.web("purple", 0.1), Color.web("yellow", 0.1)
        };

	    rand = new Random();

        timeline = new Timeline();
//        drawCircles();
//        playAnimations();
    }

    public void playAnimations() {
        for (Circle circle: circles) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(
                            Duration.ZERO,
                            new KeyValue(circle.centerXProperty(), Math.random() * myScene.getWidth()),
                            new KeyValue(circle.centerYProperty(), rand.nextInt(500) + myScene.getHeight())
                    ),
                    new KeyFrame(
                            Duration.seconds(30),
                            new KeyValue(circle.centerXProperty(), Math.random() * myScene.getWidth()),
                            new KeyValue(circle.centerYProperty(), rand.nextInt(500) - myScene.getHeight() / 2)
                    ),
                    new KeyFrame(
                            Duration.seconds(60),
                            new KeyValue(circle.centerXProperty(), Math.random() * myScene.getWidth()),
                            new KeyValue(circle.centerYProperty(), rand.nextInt(500) * -1)
                    )
            );
        }

        timeline.setCycleCount(1);
        timeline.play();
    }

    private  void drawCircles () {
        circles = new Circle[30];

        for (int i = 0; i < circles.length; i++) {
            circles[i] = new Circle(Math.random() * myScene.getWidth(), Math.random() * myScene.getHeight(), rand.nextInt(10) + 5);
            circles[i].setFill(colors[rand.nextInt(colors.length)]);

            this.getChildren().add(circles[i]);
        }


    }
}
