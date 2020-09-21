package layer_business;

import dto.AdminDTO;
import dto.UserDTO;
import layer_data_access.repo.UserRepo;

public class Functions {
    public UserDTO loginPlayer(String mail, String password) {
        if (UserRepo.findUserByMailAndPassword(mail, password) == null)
            return null;
        //do operations on user
        return new UserDTO(UserRepo.findUserByMailAndPassword(mail, password));
    }


    public AdminDTO loginAdmin(String mail, String password) {
        if (UserRepo.findAdminByNameAndPassword(mail, password) == null)
            return null;
        //do operations on user
        return new AdminDTO(UserRepo.findAdminByNameAndPassword(mail, password));
    }
}