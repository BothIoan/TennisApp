package layer_data_access.repo.BridgeDataToLogic;

import layer_business.Bridge.User_BusinesToPresentation;
import layer_business.UserHandler;
import layer_data_access.repo.UserRepo;
import layer_presentation.util.Bridge.Match_PresentationToBusines;
import layer_presentation.util.Bridge.User_PresentationToBusines;
import model.User;

import java.util.ArrayList;
public class User_DataToLogic {
    static public void InitalizeApplicationUsers(){
        //ceva sa scoata din db tot ce este;
        ArrayList<User> users = UserRepo.loadAllUsers();

        User_BusinesToPresentation.setUsers(users);
        Match_PresentationToBusines.setUsers(users);
        User_PresentationToBusines.setUsers(users);
        UserHandler.setUsers(users);
    }
}
