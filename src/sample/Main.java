package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
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
    private Group root, menuBarGroup, bodyGroup, analogClock, digitalClock;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new Group();
        scene = new Scene(root, 580, 310, Color.web("#674172"));

        this.prepareMenuBarGroup();
        this.prepareBodyGroup();
        this.prepareRootGroup();

        primaryStage.setTitle("Hello World");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepareRootGroup()
    {
        root.getChildren().addAll(menuBarGroup, bodyGroup);
    }

    private void prepareBodyGroup()
    {
        bodyGroup = new Group();
        analogClock = new AnalogClock(scene);
        snakeController = new SnakeController(scene, 5);
        digitalClock = new DigitalClock();

        kota = new Label("Bangkalan");
        kota.setFont(new Font("Arial", 40));
        kota.setTextFill(Color.web("#EEEEEE"));
        kota.setLayoutX(330);
        kota.setLayoutY(80);

        bodyGroup.getChildren().addAll(
                analogClock, digitalClock,
                snakeController.getSnake(), snakeController.getFoodGroup(),
                kota
        );

        bodyGroup.setLayoutY(30);
    }

    private void prepareMenuBarGroup()
    {
        menuBarGroup = new Group();
        menuBarGroup.getChildren().add(new MyMenuBar(scene));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
