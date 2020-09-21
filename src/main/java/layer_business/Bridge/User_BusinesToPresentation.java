package layer_business.Bridge;

import layer_presentation.util.ModifiedMenuItems.UserDTO2MenuItem;
import layer_presentation.util.ModifiedMenuItems.UserDTO3MenuItem;
import layer_presentation.util.ModifiedMenuItems.UserDTOMenuItem;
import model.User;

import java.util.ArrayList;

public class User_BusinesToPresentation {
    private static ArrayList<User> users;

    //initializations
    static public void setUsers(ArrayList<User> users){
    User_BusinesToPresentation.users =users;
    }
    //initializations


    //functionalities
   static public ArrayList<UserDTOMenuItem> getIdNames() {
        ArrayList<UserDTOMenuItem> returnBox= new ArrayList<>();
        users.forEach((x)-> returnBox.add(UserDTOMenuItem.builder().idUser(x.getId()).name(x.getName()).build()));
        return returnBox;
    }

    static public ArrayList<UserDTO2MenuItem> getAllStrings(){
        ArrayList<UserDTO2MenuItem> returnBox = new ArrayList<>();
        users.forEach(x->returnBox.add(UserDTO2MenuItem.builder().mail(x.getMail()).name(x.getName()).password(x.getPassword()).build()));
        return returnBox;
    }

    static public ArrayList<UserDTO3MenuItem> getAllInfo(){
        ArrayList<UserDTO3MenuItem> returnBox = new ArrayList<>();
        users.forEach(x->returnBox.add(UserDTO3MenuItem.builder().mail(x.getMail()).name(x.getName()).password(x.getPassword()).idUser(x.getId()).build()));
        return returnBox;
    }
    //functionalities
}
