package layer_business;
import layer_presentation.util.Bridge.Match_PresentationToBusines;
import model.User;

import java.util.ArrayList;

public class UserHandler {
    private static ArrayList<User> users;
    //initializations
    public static void setUsers(ArrayList<User> users){
        UserHandler.users = users;
    }
    //initializations

    //functionalities
    public static void createNewUser(User user){
    users.add(user);
    }

    public static User deleteUser(int id){
       User userToDelete = users.stream().filter(x->x.getId() == id).findAny().orElse(null);
        assert userToDelete != null;
        if (userToDelete.getInMatch()){
            userToDelete.getTennisMatch().getPlayer2().setInMatch(false);
            userToDelete.getTennisMatch().getPlayer1().setInMatch(false);
            Match_PresentationToBusines.deleteMatch(userToDelete.getTennisMatch().getId());
        }
       return userToDelete;
    }
    public static User updateUser(int id, String name, String mail, String password){
       User user = users.stream().filter(x->x.getId() == id).findAny().orElse(null);
        assert user != null;
        user.setMail(mail);
       user.setName(name);
       user.setPassword(password);
       return user;
    }
    //functionalities

}
