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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Main extends Application {

    public static double panjangSceneLingkaran = 300;
    public static double tinggiSceneLingkaran = 275;
    public static double xTengah = panjangSceneLingkaran / 2;
    public static double yTengah = tinggiSceneLingkaran / 2;
    public static Label kota, jamDigital;
    private DateFormat df;
    SnakeController snakeController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 580, 280, Color.web("#674172"));
        df = new SimpleDateFormat("HH:mm:ss");
        Circle clock = new Circle(xTengah, yTengah, 120, Color.web("#BE90D4"));

        snakeController = new SnakeController(scene, 5);

        Line jarumJam = new Line(xTengah, yTengah + 10, xTengah, yTengah - clock.getRadius()/2);
        jarumJam.setStrokeWidth(4);
        jarumJam.setStroke(Color.web("#EEEEEE"));

        Line jarumMenit = new Line(xTengah, yTengah + 15, xTengah, clock.getRadius()/2.5);
        jarumMenit.setStrokeWidth(3);
        jarumMenit.setStroke(Color.web("#446CB3"));

        Line jarumDetik = new Line(xTengah, yTengah + 15, xTengah, scene.getHeight() - clock.getRadius()*2);
        jarumDetik.setStrokeWidth(2);
        jarumDetik.setStroke(Color.web("#674172"));

        root.getChildren().add(clock);
        root.getChildren().add(jarumJam);
        root.getChildren().add(jarumMenit);
        root.getChildren().add(jarumDetik);
        root.getChildren().add(
                new Circle(xTengah, yTengah, 4, Color.web("black", 0.8))
        );

        Rotate rotasiJarumJam = new Rotate(getSudutJam(), xTengah, yTengah);
        Rotate rotasiJarumMenit = new Rotate(getSudutMenit(), xTengah, yTengah);
        Rotate rotasiJarumDetik = new Rotate(getSudutDetik(), xTengah, yTengah);

        jarumJam.getTransforms().add(rotasiJarumJam);
        jarumMenit.getTransforms().add(rotasiJarumMenit);
        jarumDetik.getTransforms().add(rotasiJarumDetik);


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rotasiJarumJam.setAngle(getSudutJam());
                rotasiJarumMenit.setAngle(getSudutMenit());
                rotasiJarumDetik.setAngle(getSudutDetik());

                Date dateobj = new Date();
                String currentTime = getCurrentTime();
                primaryStage.setTitle(currentTime);
                jamDigital.setText(currentTime);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        tambahnoNggoneJam(root, scene, clock);

        kota = new Label("Bangkalan");
        kota.setFont(new Font("Arial", 40));
        kota.setTextFill(Color.web("#EEEEEE"));
        kota.setLayoutX(330);
        kota.setLayoutY(80);

        jamDigital = new Label(df.format(new Date()));
        jamDigital.setFont(new Font("Arial", 30));
        jamDigital.setTextFill(Color.web("#EEEEEE"));
        jamDigital.setLayoutX(330);
        jamDigital.setLayoutY(140);

        root.getChildren().add(kota);
        root.getChildren().add(jamDigital);

        root.getChildren().add(snakeController.getSnake());
        root.getChildren().add(snakeController.getFoodGroup());

        BigBuble buble = new BigBuble(scene);
        root.getChildren().add(buble);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void tambahnoNggoneJam(Group root, Scene scene, Circle circle) {
        for (int i = 0; i < 12; i++) {
            Line line = new Line(
                    xTengah, tinggiSceneLingkaran - circle.getRadius() * 2 - 10,
                    xTengah, tinggiSceneLingkaran - circle.getRadius() * 2 - 15
            );
            line.setStroke(Color.web("#446CB3"));
            line.setStrokeWidth(2);
            line.getTransforms().add(
                    new Rotate(
                            i * 30, xTengah, yTengah
                    )
            );
            root.getChildren().add(
                line
            );
        }
    }

    public double getSudutJam() {
        int jam = LocalTime.now().getHour();

        if (snakeController.getSnake().getLength() > 5) {
            jam += snakeController.getSnake().getLength() - 5;
        }

        if (jam > 11) {
            jam -= 12;
        }

        int menit = LocalTime.now().getMinute();
        double persentaseMenit = ((double) menit/60 * 100);
        // System.out.println("menit/60 -> " + menit + "/60 -> " + persentaseMenit);

        return jam * 30 + (30 * persentaseMenit / 100);
    }

    public int getSudutMenit() {
        return LocalTime.now().getMinute() * 6;
    }

    public int getSudutDetik() {
        return LocalTime.now().getSecond() * 6;
    }

    public static void main(String[] args) {
        launch(args);
    }


    public String getCurrentTime() {
        if (snakeController.getSnake().getLength() > 5) {
            int jam = LocalTime.now().getHour() + snakeController.getSnake().getLength() - 5;
            int menit = LocalTime.now().getMinute();
            int detik = LocalTime.now().getSecond();

            String jamStr = jam < 10 ? "0" + jam : jam + "";
            String menitStr = menit < 10 ? "0" + menit : menit + "";
            String detikStr = detik < 10 ? "0" + detik : detik + "";

            return jamStr + ":" + menit + ":" + detik;
        }

        return df.format(new Date());
    }
}
