package sample.elements;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.Date;

/**
 * Created by ibnujakaria on 11/24/16.
 */
public class AnalogClock extends Group {

    private Scene scene;
    private Line jarumJam, jarumMenit, jarumDetik;
    private Circle clock;
    private Rotate rotasiJarumJam, rotasiJarumMenit, rotasiJarumDetik;

    public static double panjangSceneLingkaran = 300;
    public static double tinggiSceneLingkaran = 275;
    public static double xTengah = panjangSceneLingkaran / 2;
    public static double yTengah = tinggiSceneLingkaran / 2 ;

    public AnalogClock (Scene scene)
    {
        this.scene = scene;
        this.initiatesUi();
        this.attachUiElements();
        this.updateClock();
    }

    private void initiatesUi()
    {
        clock = new Circle(xTengah, yTengah, 120, Color.web("#BE90D4"));

        jarumJam = new Line(xTengah, yTengah + 10, xTengah, yTengah - clock.getRadius()/2);
        jarumJam.setStrokeWidth(4);
        jarumJam.setStroke(Color.web("#EEEEEE"));

        jarumMenit = new Line(xTengah, yTengah + 15, xTengah, clock.getRadius()/2.5);
        jarumMenit.setStrokeWidth(3);
        jarumMenit.setStroke(Color.web("#446CB3"));

        jarumDetik = new Line(xTengah, yTengah + 15, xTengah, scene.getHeight() - clock.getRadius()*2);
        jarumDetik.setStrokeWidth(2);
        jarumDetik.setStroke(Color.web("#674172"));

        rotasiJarumJam = new Rotate(getSudutJam(), xTengah, yTengah);
        rotasiJarumMenit = new Rotate(getSudutMenit(), xTengah, yTengah);
        rotasiJarumDetik = new Rotate(getSudutDetik(), xTengah, yTengah);

        jarumJam.getTransforms().add(rotasiJarumJam);
        jarumMenit.getTransforms().add(rotasiJarumMenit);
        jarumDetik.getTransforms().add(rotasiJarumDetik);
    }

    private void drawLinesOfClock()
    {
        for (int i = 0; i < 12; i++) {
            Line line = new Line(
                    xTengah, tinggiSceneLingkaran - clock.getRadius() * 2 - 10,
                    xTengah, tinggiSceneLingkaran - clock.getRadius() * 2 - 15
            );
            line.setStroke(Color.web("#446CB3"));
            line.setStrokeWidth(2);
            line.getTransforms().add(
                    new Rotate(
                            i * 30, xTengah, yTengah
                    )
            );

            this.getChildren().add(
                    line
            );
        }
    }

    private void attachUiElements()
    {
        this.getChildren().addAll(
             clock, jarumJam, jarumMenit, jarumDetik, new Circle(xTengah, yTengah, 4, Color.web("black", 0.8))
        );

        drawLinesOfClock();
    }

    private void updateClock()
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rotasiJarumJam.setAngle(getSudutJam());
                rotasiJarumMenit.setAngle(getSudutMenit());
                rotasiJarumDetik.setAngle(getSudutDetik());
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private double getSudutJam() {
        int jam = LocalTime.now().getHour();

        if (jam > 11) {
            jam -= 12;
        }

        int menit = LocalTime.now().getMinute();
        double persentaseMenit = ((double) menit/60 * 100);
        // System.out.println("menit/60 -> " + menit + "/60 -> " + persentaseMenit);

        return jam * 30 + (30 * persentaseMenit / 100);
    }

    private int getSudutMenit() {
        return LocalTime.now().getMinute() * 6;
    }

    private int getSudutDetik() {
        return LocalTime.now().getSecond() * 6;
    }
}
