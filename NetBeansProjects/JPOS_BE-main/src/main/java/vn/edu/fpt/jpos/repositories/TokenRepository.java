package vn.edu.fpt.jpos.repositories;

import vn.edu.fpt.jpos.core.CodeGenerator;
import vn.edu.fpt.jpos.repositories.entities.token.TokenDAO;
import vn.edu.fpt.jpos.repositories.entities.token.TokenDTO;
import vn.edu.fpt.jpos.repositories.entities.token.TokenERROR;
import vn.edu.fpt.jpos.repositories.entities.user.UserDTO;

public class TokenRepository {

    private static TokenRepository instance;
    private final TokenDAO tokenDao = TokenDAO.getInstance();

    private TokenRepository() {
    }

    public static TokenRepository getInstance() {
        if (instance == null) {
            instance = new TokenRepository();
        }
        return instance;
    }

    public TokenDTO addNewToken(UserDTO loginUser) throws TokenERROR {
        TokenDTO token = new TokenDTO(CodeGenerator.generateRandomToken(),
                loginUser.getId(), loginUser.getRole().toString());
        return tokenDao.post(token);
    }

    public TokenDTO removeToken(TokenDTO token) throws TokenERROR {
        return tokenDao.delete(token);
    }

    public TokenDTO getToken(String tokenString) throws TokenERROR {
        return tokenDao.get(tokenString);
    }
}
