package layer_business;

import layer_business.Bridge.Match_LogicToData;
import layer_data_access.repo.GenericRepo;
import model.TennisGame;
import model.TennisMatch;
import model.TennisSet;
import model.User;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class ScoreLogic {
    private int P1GameScore;
    private int P2GameScore;
    private int P1SetScore;
    private int P2SetScore;
    private int P1MatchScore;
    private int P2MatchScore;
    private int currentSet;
    private int currentGame;
    private ArrayList<String> setScores;
    boolean finishedMatchFlag;
    boolean finishedGameFlag;
    boolean finishedSetFlag;
    TennisMatch tennisMatch;
    List<TennisSet> tennisSets;
    String returnMessage ;

  
    private boolean updateGame() {

        if ((P1GameScore < 4 && P2GameScore < 4) || Math.abs(P1GameScore - P2GameScore) < 2) {//if conditions are not met yet
                finishedGameFlag = false;//game is not over
                return true;//there shouldn't be another game after this one
        }
        if (Math.abs(P1GameScore - P2GameScore) == 2||(Math.abs(P1GameScore-P2GameScore)>=2&&((P1GameScore<4&&P2GameScore==4)||(P2GameScore<4&&P1GameScore==4)))) {//if conditions are met
                if (P1GameScore >P2GameScore) { P1SetScore++; return true;} //compute Set Score
                P2SetScore++; return true;
                // go next
        }
            returnMessage = "The game is finished if one player has minimum 4 points and with 2 points more than the other player! Difference between players at a game is more than 2, or neither reached 4 points";
            return false;
            //if conditions neither insufficient , nor sufficient , it means an input error occured (abs(P1-P2)>2)
    }

    private boolean updateSets(TennisSet tennisSet){
        finishedGameFlag = true;
        P1SetScore = 0;
        P2SetScore = 0;
        List<TennisGame> games = tennisSet.getGames();
        if(games.isEmpty())finishedGameFlag=false;
        else
        for (TennisGame tennisGame : games) {
            P1GameScore = tennisGame.getP1Score();
            P2GameScore = tennisGame.getP2Score();
            if(!finishedGameFlag) {returnMessage = "The game is finished if one player has minimum 4 points and with 2 points more than the other player! Some game is not over but you put a score to a following one";return false;}
            if(!updateGame()) return false;
        }
        setScores.add(P1SetScore + " - " + P2SetScore);
        if((P1SetScore <6 && P2SetScore <6)||Math.abs(P1SetScore - P2SetScore)<2){finishedSetFlag = false; return true;}
        if (Math.abs(P1SetScore - P2SetScore) == 2||(Math.abs(P1SetScore - P2SetScore) >=2 && ((P1SetScore<6&&P2SetScore==6)||(P1SetScore==6&&P2SetScore<6)))) {
            if (!finishedGameFlag) {returnMessage = "The Current Set is already finished, the current game is illegal"; return false;}
            if (P1SetScore>P2SetScore) {P1MatchScore++; return true;}
            P2MatchScore++; return true;
        }
        returnMessage = "The set is finished if one player has minimum 6 points and with 2 points more than the other player! Difference between players at a set is more than 2";
        return false;
     }

     private boolean updateMatchLogic(){
        returnMessage = "Operation completed!";
        P1MatchScore = 0;
        P2MatchScore = 0;
        finishedSetFlag = true;
        finishedMatchFlag = false;
         List<TennisSet> tennisSets =  tennisMatch.getSets();
         if(tennisSets.isEmpty())finishedSetFlag=false;
         else
         for (TennisSet tennisSet: tennisSets) {
             if(finishedMatchFlag) {returnMessage= "A match ends when 3 sets are won by a player. This match contiunes over that limit";return false;}
            if(!finishedSetFlag) {returnMessage = "The set is finished if one player has minimum 6 points and with 2 points more than the other player! Some set is not over but you put a score to a following oner";return false;}
            if(!updateSets(tennisSet)) return false;
            if(P1MatchScore ==3 || P2MatchScore ==3) finishedMatchFlag = true;
         }
         return true;
     }

    public boolean updateMatch(TennisMatch tennisMatch) {
        setScores = new ArrayList<>();
        this.tennisMatch = tennisMatch;
        tennisSets = tennisMatch.getSets();
        if(updateMatchLogic()){
            if(!finishedMatchFlag){
                currentSet = this.tennisMatch.getSets().size()-1;
                currentGame  = this.tennisMatch.getSets().get(currentSet).getGames().size()-1;
                if(finishedSetFlag){
                    tennisSets.add(getEmptyTennisSet());
                    finishedSetFlag = false;
                    finishedGameFlag = false;
                    currentSet++;
                    currentGame++;
                    return true;
                }
                if (finishedGameFlag) {
                    tennisSets.get(currentSet).getGames().add(getEmptyTennisGame());
                    finishedGameFlag = false;
                    currentGame++;
                    return true;
                }
                return true;///? gandesc mai tarziu ce tre sa returneze aici
            }
            tennisMatch.getPlayer2().setInMatch(false);
            tennisMatch.getPlayer1().setInMatch(false);
            return true;
        }
        return false;
    }

     private boolean incrementLogic(boolean player1) {
         returnMessage = "Operation complete";
         if (finishedMatchFlag) {
             returnMessage = "This Match is over!";
             return false;
         }
         if (player1) P1GameScore++;
         else P2GameScore++;

         if ((P1GameScore < 4 && P2GameScore < 4) || Math.abs(P1GameScore - P2GameScore) < 2) {
             return true;
         }
         currentGame++;
         finishedGameFlag = true;
         if (player1) P1SetScore++;
         else P2SetScore++;
         if ((P1SetScore < 6 && P2SetScore < 6) || Math.abs(P2SetScore - P1SetScore) < 2){
             tennisSets.get(currentSet).getGames().add(getEmptyTennisGame());
             return true;
         }
         finishedSetFlag = true;
         tennisMatch.getSets().add(getEmptyTennisSet());
         currentGame =0;
         currentSet ++;
         if(player1) P1MatchScore++;
         else P2MatchScore++;
         if((P1MatchScore ==3 || P2MatchScore ==3)){
             finishedMatchFlag = true;
             tennisMatch.getPlayer2().setInMatch(false);
             tennisMatch.getPlayer1().setInMatch(false);
         }
         return true;
     }
     public boolean increment(boolean player1) {
         if (incrementLogic(player1)) {
             if (finishedGameFlag) {
                 setScores.remove(setScores.size()-1);
                 setScores.add(P1SetScore + " - " +P2SetScore);
                 P1GameScore = 0;
                 P2GameScore = 0;
                 finishedGameFlag = false;
                 if (finishedSetFlag) {
                     setScores.add("0 - 0");
                     P1SetScore = 0;
                     P2SetScore = 0;
                     finishedSetFlag = false;
                     TennisGame tennisGame = tennisSets.get(currentSet-1).getGames().get(tennisSets.get(currentSet-1).getGames().size()-1); //urat , da nu mai am chef de chestii elegante
                     if (player1) tennisGame.P1Inc();
                     else tennisGame.P2Inc();
                     TennisSet tennisSet =tennisSets.get(tennisSets.size()-1);
                     tennisSet.setTennisMatch(tennisMatch);
                     tennisSet.getGames().get(0).setTennisSet(tennisSet);
                     Match_LogicToData.saveSet(tennisSets.get(tennisSets.size()-1));
                     GenericRepo.update(tennisGame);
                 }
                 else
                 {
                     TennisGame tennisGame = tennisSets.get(currentSet).getGames().get(currentGame - 1);
                     if (player1) tennisGame.P1Inc();
                     else tennisGame.P2Inc();
                     GenericRepo.update(tennisGame);
                     GenericRepo.save(tennisSets.get(currentSet).getGames().get(currentGame));
                 }
             }
             else {
                 TennisGame tennisGame = tennisSets.get(currentSet).getGames().get(currentGame);
                 if (player1) tennisGame.P1Inc();
                 else tennisGame.P2Inc();
                 GenericRepo.update(tennisGame);
                 return true;
             }
         }
         return false;
     }

     public void createMatch(TennisMatch tennisMatch){
        this.tennisMatch= tennisMatch;
        P1GameScore = P2GameScore = P1SetScore = P2SetScore = P2MatchScore = P1MatchScore = currentGame = currentSet = 0;
        finishedSetFlag =finishedMatchFlag= finishedGameFlag =false;
        tennisSets= tennisMatch.getSets();
        setScores = new ArrayList<>();
        setScores.add("0 - 0");
    }
    private static TennisMatch callerMatch;
    private static TennisSet caller;
    static TennisGame getEmptyTennisGame() {
        TennisGame tennisGame = new TennisGame();
        tennisGame.setP1Score(0);
        tennisGame.setP2Score(0);
        tennisGame.setTennisSet(caller);
        return tennisGame;
    }
    static TennisSet getEmptyTennisSet(){
        ArrayList<TennisGame> tennisGames = new ArrayList<>();
        tennisGames.add(getEmptyTennisGame());

        return TennisSet.builder().id(0).games(tennisGames).build();

    }
    public static TennisMatch getEmptyTennisMatch(User user1,User user2) {
        ArrayList<TennisSet> sets = new ArrayList<>();
        sets.add(getEmptyTennisSet());
        return TennisMatch.builder().player1(user1).player2(user2).sets(sets).id(0).build();

    }
    public String getReturnMessage(){
        return returnMessage;
    }

    public int getP1MatchScore() {
        return P1MatchScore;
    }

    public int getP2MatchScore() {
        return P2MatchScore;
    }

    public TennisMatch getTennisMatch(){
        return tennisMatch;
    }

    public ArrayList<String> getSetScore(){
        if (finishedMatchFlag)setScores.remove(setScores.size()-1);
        return setScores;
            //ArrayList<String> strings = new ArrayList<>(setScores); nici asta nu stiu dupa ce ii pusa , de returnat tot setScores returna
        //strings.add(P1SetScore + " - " + P2SetScore);return strings; aici era si asta la un moment dat. Nu stiu de ce am pus-o , da daca ii bai la setScores, de aici ii
    }
}




