package layer_presentation.util.Descriptions;

import layer_presentation.util.ModifiedMenuItems.SetDTO;
import lombok.Setter;
import java.util.ArrayList;
@Setter
public class SetDescription2 implements Description{
    ArrayList <SetDTO> data;

    @Override
    public String getData1() {
        return null;
    }

    @Override
    public ArrayList<SetDTO> getData2() {
        return data;
    }
}
