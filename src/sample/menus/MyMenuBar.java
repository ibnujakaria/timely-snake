package sample.menus;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import sample.menus.mode.Mode;

/**
 * Created by ibnujakaria on 11/24/16.
 */
public class MyMenuBar extends MenuBar {

    private Scene scene;
    private Mode modeMenu;

    public MyMenuBar (Scene scene)
    {
        this.scene = scene;

        this.prefWidthProperty().bind(scene.widthProperty());

        this.prepareMenu();
    }

    private void prepareMenu()
    {
        modeMenu = new Mode();

        getMenus().addAll(modeMenu);
    }
}
