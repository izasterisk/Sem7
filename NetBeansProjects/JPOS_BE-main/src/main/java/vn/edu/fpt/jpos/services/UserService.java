package vn.edu.fpt.jpos.services;

import java.util.List;
import java.util.stream.Collectors;
import vn.edu.fpt.jpos.repositories.UserRepository;
import vn.edu.fpt.jpos.repositories.entities.user.UserDTO;
import vn.edu.fpt.jpos.repositories.entities.user.UserERROR;

public class UserService {

    private static UserService instance = null;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public List<UserDTO> getAll() throws UserERROR {
        UserRepository repo = UserRepository.getInstance();
        return repo.getAll();
    }

    public UserDTO getByPhone(String phone) throws UserERROR {
        UserRepository repo = UserRepository.getInstance();
        List<UserDTO> list = repo.getAll();
        return list.stream()
                .filter(u -> u.getPhone() == null ? false
                : u.getPhone().equals(phone))
                .collect(Collectors.toList()).get(0);
    }

    public UserDTO addNewUser(UserDTO user) throws UserERROR {
        UserRepository repo = UserRepository.getInstance();
        return repo.addNewUser(user);
    }

    public UserDTO getUserById(String id) throws UserERROR {
        UserRepository repo = UserRepository.getInstance();
        List<UserDTO> list = repo.getAll();
        return list.stream()
                .filter(u -> u.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }
}
