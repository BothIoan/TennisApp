package layer_data_access.repo.BridgeDataToLogic;

import layer_business.Bridge.Match_BusinesToPresentation;
import layer_business.MatchHandler;
import layer_business.ScoreLogic;
import layer_data_access.repo.MatchRepo;
import layer_presentation.util.Bridge.Match_PresentationToBusines;
import model.TennisMatch;

import java.util.ArrayList;
import java.util.Scanner;

public class Match_DataToLogic {
    static public void initializeApplicationMatch(){
        ArrayList<TennisMatch>  tennisMatches = MatchRepo.loadMatches();
        tennisMatches.forEach(x->{x.getPlayer1().setInMatch(true);x.getPlayer2().setInMatch(true);x.getPlayer2().setTennisMatch(x);x.getPlayer1().setTennisMatch(x);});
        MatchHandler.setMatches(tennisMatches);
        tennisMatches.forEach(MatchHandler::load);
        Match_PresentationToBusines.setMatches(tennisMatches);
        Match_BusinesToPresentation.setTennisMatches(tennisMatches);
    }
}
