package sample.menus.alarm;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by ibnujakaria on 11/24/16.
 */
public class Alarm extends Menu {

    public Alarm ()
    {
        super ("Alarm");

        this.addMenuItems();
    }

    private void addMenuItems()
    {
        MenuItem toggleAlarm = new MenuItem("Toggle Alarm");
        MenuItem setNewAlarm = new MenuItem("Set Alarm");

        this.getItems().addAll(toggleAlarm, setNewAlarm);
    }
}
