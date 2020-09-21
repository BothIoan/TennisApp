package layer_presentation.util.Bridge;
import layer_business.Bridge.Match_LogicToData;
import layer_business.MatchHandler;
import layer_business.ScoreLogic;
import layer_presentation.util.ModifiedMenuItems.SetDTO;
import model.TennisGame;
import model.TennisMatch;
import model.TennisSet;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Match_PresentationToBusines {
    static ArrayList<TennisMatch> tennisMatches;
    static ArrayList<ScoreLogic> tennisMatchesAllData;
    static ArrayList<User> users;

    //initialization
    static public void setUsers (ArrayList<User> users){
        Match_PresentationToBusines.users = users;
    }
    static public void setMatches(ArrayList<TennisMatch> tennisMatches){
    Match_PresentationToBusines.tennisMatches = tennisMatches;
    }
    static public void setMatchesAllData (ArrayList<ScoreLogic> tennisMatchesAllData){
        Match_PresentationToBusines.tennisMatchesAllData = tennisMatchesAllData;
    }
    //initialization


    //functionality
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    static public String createNewMatch(int id1, int id2) {
        User p1 = users.stream().filter(x -> x.getId()==id1).findAny().get();
        User p2 = users.stream().filter(x -> x.getId()==id2).findAny().get();
        if(p1.getInMatch()||p2.getInMatch())return "One of the players is already in a match";
        TennisMatch created = MatchHandler.creatNewMatch(p1,p2);
        Match_LogicToData.saveMatch(created);
        p1.setTennisMatch(created);
        p2.setTennisMatch(created);
        p1.setInMatch(true);
        p2.setInMatch(true);
        return "Match created";
    }

    static public void deleteMatch(int id){
        TennisMatch tennisMatch = MatchHandler.getById(id);
        Match_LogicToData.deleteMatch(tennisMatch);
        MatchHandler.deleteMatch(tennisMatch);
    }

    static public String updateMatch(ArrayList<ArrayList<SetDTO>> sets,int id){
        TennisMatch persistent = MatchHandler.getById(id);
        User p1 = persistent.getPlayer1();
        User p2 = persistent.getPlayer2();
        TennisMatch tennisMatch = MatchHandler.updateMatch(sets,id);
        if (MatchHandler.getValidation())
            Match_LogicToData.deleteMatch(persistent);
            Match_LogicToData.saveMatch(tennisMatch);
        return MatchHandler.getReturnMessage();
        //wtf s-ar putea sa fie OP Hibernateu
    }

    static public void incrementMatch(int callerId) {
        //heavily Based on relations that will be enstablished at DBconnection , might not work properly for now
        User user = users.stream().filter(x -> x.getId() == callerId).findAny().orElse(null);
            assert user != null;
            if (user.getInMatch()) {
                ScoreLogic scoreLogic = tennisMatchesAllData.stream().filter(x -> x.getTennisMatch().getId() == user.getTennisMatch().getId()).findAny().orElse(null);
                assert scoreLogic != null;
                if (callerId == user.getTennisMatch().getPlayer1().getId())
                    scoreLogic.increment(true);
                else scoreLogic.increment(false);
             }
        }
    }
    //functionality