package layer_presentation.util.Bridge;

import layer_business.Bridge.Match_BusinesToPresentation;
import layer_business.Bridge.User_LogicToData;
import layer_business.UserHandler;
import layer_presentation.util.AlertBox;
import model.User;

import java.util.ArrayList;

public class User_PresentationToBusines {

    private static ArrayList<User>users;

    //initializations
    public static void setUsers(ArrayList<User> users) {
        User_PresentationToBusines.users = users;
    }
    //initializations

    //functionalities
    public static void createNewUser(String name, String mail, String password){
        users.stream().filter(y->y.getMail().equals(mail)).findAny().ifPresentOrElse(x->AlertBox.display("Bad","Mail already registered"),()->
        {
            User toSave = User_LogicToData.saveUser(User.builder().inMatch(false).mail(mail).name(name).password(password).build());
            UserHandler.createNewUser(toSave);
        });
    }

    public static void deleteUser(int id){
        User user = UserHandler.deleteUser(id);
        User_LogicToData.deleteUser(user);
        users.remove(user);
    }
    public static void updateUser(int id, String newName, String newMail, String newPassword){
        users.stream().dropWhile(x->x.getId()==id).filter(y->y.getMail().equals(newMail)).findAny().ifPresentOrElse(z-> AlertBox.display("Bad","Mail already registered"),()->User_LogicToData.updateUser(UserHandler.updateUser(id,newName,newMail,newPassword)));
    }
    //functionalities
}
