package vn.edu.fpt.jpos.repositories;

import java.util.List;
import java.util.stream.Collectors;
import vn.edu.fpt.jpos.repositories.entities.user.UserDAO;
import vn.edu.fpt.jpos.repositories.entities.user.UserDTO;
import vn.edu.fpt.jpos.repositories.entities.user.UserERROR;

public class UserRepository {

    private static UserRepository instance;
    private final UserDAO userDao = UserDAO.getInstance();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public List<UserDTO> getAll() throws UserERROR {
        return userDao.gets();
    }

    public UserDTO findByEmail(String email) throws UserERROR {
        List<UserDTO> userList = userDao.gets();
        for (UserDTO user : userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        throw new UserERROR("This username does not exist");
    }

    public UserDTO addNewUser(UserDTO userInfo) throws UserERROR {
        UserDTO user = userDao.post(userInfo);
        return user;
    }
}
