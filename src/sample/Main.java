package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import sample.elements.AnalogClock;
import sample.elements.DigitalClock;
import sample.elements.snake.SnakeController;
import sample.menus.MyMenuBar;

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
