package app.pooi.authserver.config.security;

import app.pooi.authserver.user.LoginUser;
import app.pooi.authserver.user.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginUserService loginUserService;

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<LoginUser> loginUser = this.loginUserService.findById(username);

        if (!loginUser.isPresent()) {

            throw new UsernameNotFoundException(
                    this.messages.getMessage("JdbcDaoImpl.notFound",
                            new Object[]{username}, "Username {0} not found"));
        }


        return new User(username, loginUser.get().getPassword(), Collections.EMPTY_LIST);
    }
}
