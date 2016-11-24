package sample.menus.mode;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import sample.menus.MyMenuBar;

/**
 * Created by ibnujakaria on 11/24/16.
 */
public class Mode extends Menu {

    private MyMenuBar menuBar;
    private MenuItem snakeItem, digitalItem, analogItem;

    public Mode (MyMenuBar menuBar)
    {
        super ("Mode");

        this.menuBar = menuBar;
        this.addItems();
        this.addListeners();
    }

    private void addListeners()
    {
        this.snakeItem.setOnAction(event -> {
            this.onSnakeItemClick();
        });
    }

    private void onSnakeItemClick()
    {
        this.menuBar.getMain().toggleSnake();
    }

    private void addItems ()
    {
        snakeItem = new MenuItem("Snakely");
        digitalItem = new MenuItem("Digital");
        analogItem = new MenuItem("Analog");

        this.getItems().addAll(snakeItem, digitalItem, analogItem);
    }
}
