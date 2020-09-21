package layer_presentation.util.Descriptions;

import layer_presentation.util.ModifiedMenuItems.SetDTO;
import lombok.Setter;

import java.util.ArrayList;
@Setter
public class SetDescription1 implements Description{
    String data;


    @Override
    public String getData1() {
        return data;
    }

    @Override
    public ArrayList<SetDTO> getData2() {
        return null;
    }
}
