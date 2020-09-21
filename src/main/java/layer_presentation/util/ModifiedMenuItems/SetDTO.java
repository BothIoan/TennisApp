package layer_presentation.util.ModifiedMenuItems;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SetDTO {
    String p1Score;
    String p2Score;
    SetDTO(Integer p1,Integer p2){
        p1Score= p1.toString();
        p2Score= p2.toString();
    }
}
