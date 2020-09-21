package layer_presentation.util.ModifiedMenuItems;

import javafx.scene.control.MenuItem;
import layer_presentation.util.Descriptions.Description;

public class MatchMenuItem extends MenuItem {
    public Description getDescription() {
        return description;
    }

    private Description description;
    public MatchMenuItem(Description description){
        this.description = description;
    }
}
