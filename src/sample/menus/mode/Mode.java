package sample.menus.mode;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by ibnujakaria on 11/24/16.
 */
public class Mode extends Menu {

    public Mode ()
    {
        super ("Mode");

        addItems();
    }

    private void addItems ()
    {
        MenuItem snakeItem = new MenuItem("Snakely");
        MenuItem digitalItem = new MenuItem("Digital");
        MenuItem analogItem = new MenuItem("Analog");

        this.getItems().addAll(snakeItem, digitalItem, analogItem);
    }
}
