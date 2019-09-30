package app.pooi.authserver.config.security;

import app.pooi.authserver.infrastructure.user.User;
import app.pooi.authserver.infrastructure.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<User> loginUser = this.userRepository.findById(username);

        if (!loginUser.isPresent()) {

            throw new UsernameNotFoundException(
                    this.messages.getMessage("JdbcDaoImpl.notFound",
                            new Object[]{username}, "Username {0} not found"));
        }


        return new org.springframework.security.core.userdetails.User(username, loginUser.get().getPassword(), Collections.emptyList());
    }
}
