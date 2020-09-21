package layer_business.Bridge;

import layer_data_access.repo.GenericRepo;
import model.User;

public class User_LogicToData {


    //initializations

    //initializations

    //functionalities
    public static User saveUser(User toSave){
        toSave.setId(GenericRepo.save(toSave));
        return toSave;
    }
    public static void updateUser(User user){
    GenericRepo.update(user);
    }
    public static void deleteUser (User user){
        GenericRepo.delete(user);
    }
    //functionalities
}
