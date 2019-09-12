package app.pooi.authserver.user;

import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginUserService {

    @Delegate
    @Autowired
    private LoginUserRepository loginUserRepository;

    public LoginUser saveUser(LoginUser loginUser) {
        return loginUserRepository.save(loginUser);
    }
}
