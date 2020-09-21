package layer_presentation.util.ModifiedMenuItems;

import javafx.scene.control.MenuItem;
import layer_presentation.util.Descriptions.Description;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MatchDTO2MenuItem extends MenuItem {
    int idMatch;
    String nameMatch;
    String p1MatchScore;
    String p2MatchScore;
}
