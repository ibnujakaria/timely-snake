package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.Duration;
import sample.elements.AnalogClock;
import sample.elements.DigitalClock;
import sample.menus.MyMenuBar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Main extends Application {

    public static Label kota;
    private SnakeController snakeController;
    private Group root, analogClock, digitalClock;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new Group();
        scene = new Scene(root, 580, 280, Color.web("#674172"));

        analogClock = new AnalogClock(scene);
        root.getChildren().add(analogClock);
        snakeController = new SnakeController(scene, 5);

        kota = new Label("Bangkalan");
        kota.setFont(new Font("Arial", 40));
        kota.setTextFill(Color.web("#EEEEEE"));
        kota.setLayoutX(330);
        kota.setLayoutY(80);

        root.getChildren().add(kota);

        digitalClock = new DigitalClock();
        root.getChildren().add(digitalClock);

        root.getChildren().add(snakeController.getSnake());
        root.getChildren().add(snakeController.getFoodGroup());

        addMenuBar();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addMenuBar ()
    {
        root.getChildren().add(new MyMenuBar(scene));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
