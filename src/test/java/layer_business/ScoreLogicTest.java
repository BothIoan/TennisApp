package layer_business;

import model.TennisMatch;
import model.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class ScoreLogicTest {
    ScoreLogic scoreLogic;

    @BeforeEach
    public void setUp(){
        scoreLogic = new ScoreLogic();
        scoreLogic.createMatch(ScoreLogic.getEmptyTennisMatch(User.builder().inMatch(true).name("n").build(),User.builder().name("m").inMatch(true).build()));
    }

    @Test
    public void createMatch() {
        setUp();
        assertEquals(0,scoreLogic.getP1MatchScore());
        assertEquals(0,scoreLogic.getP1MatchScore());
        assertEquals(1,scoreLogic.getTennisMatch().getSets().size());
        assertEquals(1,scoreLogic.getTennisMatch().getSets().get(0).getGames().size());
    }
    @Test
    public void increment() {
        setUp();
    scoreLogic.increment(true);
    scoreLogic.increment(false);
    assertEquals(1,scoreLogic.getTennisMatch().getSets().get(0).getGames().get(0).getP2Score());
    assertEquals(1,scoreLogic.getTennisMatch().getSets().get(0).getGames().get(0).getP1Score());
    scoreLogic.increment(false);
    assertEquals(2,scoreLogic.getTennisMatch().getSets().get(0).getGames().get(0).getP2Score());
    }


    @Test
    public void updateMatch() {
    ScoreLogic scoreLogic2 = new ScoreLogic();
    scoreLogic2.createMatch(ScoreLogic.getEmptyTennisMatch(User.builder().build(),User.builder().build()));
    scoreLogic2.increment(true);
    scoreLogic2.increment(true);
    scoreLogic2.increment(true);
    scoreLogic2.increment(true);
    scoreLogic2.increment(true);
    scoreLogic.updateMatch(scoreLogic2.getTennisMatch());
    }




}