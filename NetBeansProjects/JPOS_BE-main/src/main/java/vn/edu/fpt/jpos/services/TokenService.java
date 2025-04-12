package vn.edu.fpt.jpos.services;

import vn.edu.fpt.jpos.repositories.TokenRepository;
import vn.edu.fpt.jpos.repositories.UserRepository;
import vn.edu.fpt.jpos.repositories.entities.token.TokenDTO;
import vn.edu.fpt.jpos.repositories.entities.token.TokenERROR;
import vn.edu.fpt.jpos.repositories.entities.user.UserDTO;
import vn.edu.fpt.jpos.repositories.entities.user.UserERROR;
import vn.edu.fpt.jpos.repositories.enums.UserRoleEnum;

public class TokenService {

    private static TokenService instance = null;

    private TokenService() {
    }

    public static TokenService getInstance() {
        if (instance == null) {
            instance = new TokenService();
        }
        return instance;
    }

    public TokenDTO checkLogin(String email, String password)
            throws UserERROR, TokenERROR {
        UserRepository userRepo = UserRepository.getInstance();
        TokenRepository tokenRepo = TokenRepository.getInstance();
        UserDTO loginUser = userRepo.findByEmail(email);
        if (password.equals(loginUser.getPassword())) {
            TokenDTO token = tokenRepo.addNewToken(loginUser);
            return token;
        } else {
            throw new UserERROR("Wrong password for this user!");
        }
    }

    public TokenDTO register(UserDTO userInfo)
            throws UserERROR, TokenERROR {
        UserRepository userRepo = UserRepository.getInstance();
        userInfo.setRole(UserRoleEnum.CUSTOMER);
        UserDTO loginUser = userRepo.addNewUser(userInfo);
        TokenDTO token = checkLogin(loginUser.getEmail(), loginUser.getPassword());
        return token;
    }

    public TokenDTO logout(TokenDTO token) {
        TokenRepository tokenRepo = TokenRepository.getInstance();
        return tokenRepo.removeToken(token);
    }

    public TokenDTO getToken(String tokenString) throws TokenERROR {
        TokenRepository repo = TokenRepository.getInstance();
        return repo.getToken(tokenString);

    }
}
