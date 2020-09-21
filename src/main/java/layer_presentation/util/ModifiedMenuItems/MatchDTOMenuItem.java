package layer_presentation.util.ModifiedMenuItems;
import javafx.scene.control.MenuItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class MatchDTOMenuItem extends MenuItem {
    int idMatch;
    String nameMatch;
}
