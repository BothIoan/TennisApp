package layer_presentation.util.ModifiedMenuItems;

import dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.event.service.spi.EventActionWithParameter;

@Setter
@Getter
@Builder
public class UserDTOMenuItem extends MenuItem  {//for the DTO with name and ID
    int idUser;
    String name;
}
