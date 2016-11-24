package sample.menus;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import sample.Main;
import sample.menus.alarm.Alarm;
import sample.menus.mode.Mode;

/**
 * Created by ibnujakaria on 11/24/16.
 */
public class MyMenuBar extends MenuBar {

    private Scene scene;
    private Main main;
    private Menu modeMenu, alarmMenu;

    public MyMenuBar (Main main)
    {
        this.main = main;
        this.scene = main.getScene();

        this.prefWidthProperty().bind(scene.widthProperty());

        this.prepareMenu();
    }

    private void prepareMenu()
    {
        modeMenu = new Mode(this);
        alarmMenu = new Alarm();

        getMenus().addAll(modeMenu, alarmMenu);
    }

    public Main getMain()
    {
        return main;
    }
}
