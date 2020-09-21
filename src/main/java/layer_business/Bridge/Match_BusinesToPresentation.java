package layer_business.Bridge;

import layer_business.ScoreLogic;
import layer_presentation.util.ModifiedMenuItems.MatchDTO2MenuItem;
import layer_presentation.util.ModifiedMenuItems.MatchDTOMenuItem;
import layer_presentation.util.ModifiedMenuItems.SetDTO;
import model.TennisGame;
import model.TennisMatch;

import java.util.ArrayList;
import java.util.List;

public class Match_BusinesToPresentation {

    private static ArrayList<TennisMatch> tennisMatches;
    private static ArrayList<ScoreLogic> tennisMatchesAllData;

    //initializations
    public static void setTennisMatches(ArrayList<TennisMatch> tennisMatches) {
        Match_BusinesToPresentation.tennisMatches = tennisMatches;
    }
    public static void setTennisMatchesAllData(ArrayList<ScoreLogic> tennisMatchesAllData){
        Match_BusinesToPresentation.tennisMatchesAllData = tennisMatchesAllData;
    }
    //initializations

    //functionalities
    public static ArrayList<MatchDTOMenuItem> getTennisMatchNameId(){
        ArrayList<MatchDTOMenuItem> matchDTOMenuItems = new ArrayList<>();
        tennisMatches.forEach(x->matchDTOMenuItems.add(MatchDTOMenuItem.builder().idMatch(x.getId()).nameMatch(x.getPlayer1().getName()+ "-" + x.getPlayer2().getName()).build()));
        return matchDTOMenuItems;
    }
    public static ArrayList<MatchDTO2MenuItem> getTennisMatch(){
        ArrayList<MatchDTO2MenuItem> matchDTO2MenuItems = new ArrayList<>();
        tennisMatchesAllData.forEach((x)->matchDTO2MenuItems.add(MatchDTO2MenuItem.builder().idMatch(x.getTennisMatch().getId()).nameMatch(x.getTennisMatch().getPlayer1().getName()+ "-" + x.getTennisMatch().getPlayer2().getName()).p1MatchScore(Integer.toString(x.getP1MatchScore())).p2MatchScore((Integer.toString(x.getP2MatchScore()))).build()));
        return matchDTO2MenuItems;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static ArrayList<String> getSets(int id){
        ArrayList<String> strings = tennisMatchesAllData.stream().filter(x->x.getTennisMatch().getId()==id).findAny().get().getSetScore();
        return strings;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static String getGames(int id1, int id2){
        List<TennisGame> tennisGames =tennisMatchesAllData.stream().filter(x->x.getTennisMatch().getId()==id1).findAny().get().getTennisMatch().getSets().get(id2).getGames();
        StringBuilder returnBox =new StringBuilder();
        tennisGames.forEach(x->returnBox.append(" | " + x.getP1Score() +"-"+ x.getP2Score()));
        return returnBox.toString()+" |";
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static ArrayList<SetDTO> getGames2(int id1, int id2){
        List<TennisGame> tennisGames =tennisMatchesAllData.stream().filter(x->x.getTennisMatch().getId()==id1).findAny().get().getTennisMatch().getSets().get(id2).getGames();
        ArrayList<SetDTO> returnBox =new ArrayList<>();
        tennisGames.forEach(x->returnBox.add(SetDTO.builder().p1Score(x.getP1Score()+"").p2Score(x.getP2Score()+"").build()));
        return returnBox;
    }
    //functionalities
}
