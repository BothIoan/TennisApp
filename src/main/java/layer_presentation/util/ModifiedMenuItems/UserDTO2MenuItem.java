package layer_presentation.util.ModifiedMenuItems;

import javafx.scene.control.MenuItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class UserDTO2MenuItem extends MenuItem {
    String name;
    String mail;
    String password;
}
