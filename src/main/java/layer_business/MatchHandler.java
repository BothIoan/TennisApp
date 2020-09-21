package layer_business;

import layer_business.Bridge.Match_BusinesToPresentation;
import layer_presentation.util.Bridge.Match_PresentationToBusines;
import layer_presentation.util.ModifiedMenuItems.SetDTO;
import model.TennisGame;
import model.TennisMatch;
import model.TennisSet;
import model.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MatchHandler {

    static ArrayList<TennisMatch> tennisMatches;
    static ArrayList<ScoreLogic> tennisMatchesData;
    static String returnMessage;
    static boolean valid;
    static public void setMatches( ArrayList<TennisMatch> tennisMatches){
        MatchHandler.tennisMatches = tennisMatches;
        tennisMatchesData= new ArrayList<>();
        Match_BusinesToPresentation.setTennisMatchesAllData(tennisMatchesData);
        Match_PresentationToBusines.setMatchesAllData(tennisMatchesData);
    }
    static public TennisMatch creatNewMatch(User user1,User user2) {
    TennisMatch newTennisMatch = ScoreLogic.getEmptyTennisMatch(user1,user2);
    link(newTennisMatch);
    ScoreLogic newScoreLogic = new ScoreLogic();
    newScoreLogic.createMatch(newTennisMatch);
    tennisMatches.add(newTennisMatch);
    tennisMatchesData.add(newScoreLogic);
    return newTennisMatch;
    }

    static public TennisMatch getById(int id){
        return tennisMatches.stream().filter(x->x.getId() == id).findAny().orElse(null);
    }
    static public void deleteMatch(TennisMatch tennisMatch){
        tennisMatchesData.removeIf(x->x.tennisMatch.getId()==tennisMatch.getId());
            tennisMatch.getPlayer2().setInMatch(false);
            tennisMatch.getPlayer1().setInMatch(false);
            tennisMatches.remove(tennisMatch);
    }

    static public TennisMatch updateMatch(@org.jetbrains.annotations.NotNull ArrayList<ArrayList<SetDTO>> sets, int id){
        ArrayList<TennisSet> inputSets=new ArrayList<>();
        sets.forEach(x -> {
            ArrayList<TennisGame> tennisGame = x.stream().map(y -> TennisGame.builder().p1Score(Integer.parseInt(y.getP1Score())).p2Score(Integer.parseInt(y.getP2Score())).build()).collect(Collectors.toCollection(ArrayList::new));
            inputSets.add(TennisSet.builder().games(tennisGame).build());
        });
        TennisMatch tennisMach = TennisMatch.builder().sets(inputSets).build();
        ScoreLogic scoreLogic = new ScoreLogic();
        boolean replaceFlag = scoreLogic.updateMatch(tennisMach);
        //aici ar tre trecut prima data prin DB sa isi ia un ID
        //update score logics
        if (replaceFlag) {
            tennisMatchesData.stream().filter(x->x.getTennisMatch().getId() == id).findAny().ifPresent(x->{
                tennisMach.setPlayer1(x.getTennisMatch().getPlayer1());
                tennisMach.getPlayer1().setTennisMatch(tennisMach);
                tennisMach.setPlayer2(x.getTennisMatch().getPlayer2());
                tennisMach.getPlayer2().setTennisMatch(tennisMach);
                tennisMatches.add(tennisMach);
            });
            tennisMatchesData.removeIf(x->x.getTennisMatch().getId()==id);
            tennisMatchesData.add(scoreLogic);
            MatchHandler.link(tennisMach);
        }
        MatchHandler.returnMessage= scoreLogic.getReturnMessage();
        valid=replaceFlag;
        return tennisMach;

    }

    static public boolean load(TennisMatch tennisMatch){
        ScoreLogic scoreLogic = new ScoreLogic();
        boolean validFlag = scoreLogic.updateMatch(tennisMatch);
        if (validFlag)
        tennisMatchesData.add(scoreLogic);
        return validFlag;
    }

    static public void link(TennisMatch tennisMatch){
        tennisMatch.getSets().forEach(x->{
            x.setTennisMatch(tennisMatch);
            x.getGames().forEach(y->{
                y.setTennisSet(x);
            });
        });
    }

    public static String getReturnMessage(){
        return returnMessage;
    }
    public static boolean  getValidation(){
            return valid;
    }
}
