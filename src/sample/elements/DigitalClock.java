package sample.elements;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by ibnujakaria on 11/24/16.
 */
public class DigitalClock extends Group {

    private Label label;
    private DateFormat df;

    public DigitalClock ()
    {
        df = new SimpleDateFormat("HH:mm:ss");

        this.prepareLabel();
        this.updateClock();
    }

    private void prepareLabel()
    {
        label = new Label(df.format(new Date()));
        label.setFont(new Font("Arial", 30));
        label.setTextFill(Color.web("#EEEEEE"));
        label.setLayoutX(330);
        label.setLayoutY(140);

        this.getChildren().addAll(label);
    }

    private String getCurrentTime()
    {
        return df.format(new Date());
    }

    private void updateClock ()
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText(getCurrentTime());
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
