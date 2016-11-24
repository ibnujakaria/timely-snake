package sample.elements.snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import sample.Main;

import java.util.Random;

/**
 * Created by ibnujakaria on 17/11/16.
 */
public class SnakeController {
    private Snake snake;
    private Scene scene;
    private Group foodGroup;
    private Circle food;
    private Random rand;
    private int speed = 80;
    private Timeline timeline;

    public SnakeController (Scene scene, int length) {
        snake = new Snake(scene, length);
        rand = new Random();

        this.scene = scene;

        prepareFood();
        addListeners();
        moving();
    }

    private void prepareFood () {
        foodGroup = new Group();
        food = new Circle(5, Color.web("black", 0.5));
        foodGroup.getChildren().add(food);

        randomFood();
    }

    private void randomFood () {
        food.setCenterX(rand.nextInt((int) scene.getWidth()) / 10 * 10);
        food.setCenterY(rand.nextInt((int) scene.getHeight()) / 10 * 10);
    }

    private void addListeners () {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        snake.turnUp();
                        break;
                    case RIGHT:
                        snake.turnRight();
                        break;
                    case DOWN:
                        snake.turnDown();
                        break;
                    case LEFT:
                        snake.turnLeft();
                        break;
                    case SPACE:
                        snake.eat();
                        randomFood();
                        break;
                    case ENTER:
                        snake.setLength(5);
                        Main.kota.setText("Bangkalan");
                        Main.kota.setFont(new Font("Arial", 40));
                        Main.kota.setTextFill(Color.web("#EEEEEE"));
                        timeline.playFromStart();
                }
            }
        });
    }

    public void moving () {
        timeline = new Timeline(new KeyFrame(Duration.millis(speed), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                snake.moving();

                // cek makanan
                if (snake.getHead().getCenterX() == food.getCenterX() &&
                        snake.getHead().getCenterY() == food.getCenterY()) {
                    System.out.println("Makan!");
                    randomFood();
                    snake.eat();
                    Main.kota.setText("Ayo!");
                }

                cekMati();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public Group getFoodGroup() {
        return foodGroup;
    }

    public Snake getSnake () {
        return snake;
    }

    public void cekMati () {
        if (mati()) {
            Main.kota.setFont(new Font("Arial", 25));
            Main.kota.setTextFill(Color.RED.brighter().brighter());
            Main.kota.setWrapText(true);
            Main.kota.setMaxWidth(200);
            Main.kota.setText("Tombol enter untuk ulang");
            timeline.stop();
        }
    }

    public boolean mati () {
        for (int i = 1; i < snake.getLength(); i++) {
            Atom body = snake.getAtoms()[i];
            if (snake.getHead().getCenterX() == body.getCenterX() && snake.getHead().getCenterY() == body.getCenterY()) {
                return true;
            }
        }
        return false;
    }
}
