package layer_presentation.util.Descriptions;

import layer_presentation.util.ModifiedMenuItems.SetDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Builder
@Setter
@Getter
public class UserDescription implements Description{
    String idUser;

    @Override
    public String getData1() {
        return idUser;
    }

    @Override
    public ArrayList<SetDTO> getData2() {
        return null;
        //notNeeded
    }
}
