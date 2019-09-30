package app.pooi.authserver.user.registration;

import app.pooi.authserver.infrastructure.user.User;
import app.pooi.authserver.infrastructure.user.UserRepository;
import app.pooi.authserver.web.user.registration.RegisterUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerNewUserAccount(RegisterUserDto registerUser) throws EmailExistsException {

        if (userRepository.existsById(registerUser.getEmail())) {
            throw new EmailExistsException("");
        }

        final User user = new User().setUsername(registerUser.getEmail())
                .setPassword(passwordEncoder.encode(registerUser.getPassword()))
                .setEnable(true)
                .setLastLoginTime(null);

        userRepository.saveAndFlush(user);
    }

}
