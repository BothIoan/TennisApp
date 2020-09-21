package layer_data_access.repo;

import config.HibernateConfig;
import model.TennisGame;
import model.TennisMatch;
import model.TennisSet;
import model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

public class MatchRepo {
   static final Session session =GenericRepo.getSession();

    public static ArrayList<TennisMatch> loadMatches(){
        Transaction transaction = session.beginTransaction();
        List<TennisMatch> resultList = session.createQuery("from TennisMatch",TennisMatch.class).getResultList();
        transaction.commit();
        return new ArrayList<>(resultList);
    }



}
