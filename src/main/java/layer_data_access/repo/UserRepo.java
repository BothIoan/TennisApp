package layer_data_access.repo;

import config.HibernateConfig;
import layer_presentation.util.AlertBox;
import model.Admin;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class    UserRepo {

    private static Session session = GenericRepo.getSession();

    public static User findUserByMailAndPassword(String mail,String password) {

        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.mail=:mail and u.password=:password", User.class);
        query.setParameter("mail", mail);
        query.setParameter("password",password);
        if (query.uniqueResult()==null){
            AlertBox.display("Not good","credentials not good");
            transaction.commit();
            return null;
        }
        else {
            User user = query.uniqueResult();
            transaction.commit();
            return user;
        }
    }
    public static Admin findAdminByNameAndPassword(String mail, String password) {

        Transaction transaction = session.beginTransaction();
        Query<Admin> query = session.createQuery("from Admin u where u.mail=:mail and u.password=:password", Admin.class);
        query.setParameter("mail", mail);
        query.setParameter("password",password);
        if (query.uniqueResult()==null){
            AlertBox.display("Not good","credentials not good");
            transaction.commit();
            return null;
        }
        else {
            Admin user = query.uniqueResult();
            transaction.commit();
            return user;
        }
    }
    public static ArrayList<User> loadAllUsers(){
        Transaction transaction = session.beginTransaction();
         List<User> resultList = session.createQuery("from User u",User.class).getResultList();
        transaction.commit();
        return new ArrayList<User>(resultList);
    }
}

