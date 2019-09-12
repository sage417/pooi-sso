package app.pooi.authserver.web.user.registration;

import app.pooi.authserver.user.LoginUser;
import app.pooi.authserver.user.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    LoginUserService loginUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/user/registration")
    public String showRegistrationForm(@RequestBody UserDto userDto, BindingResult result) {

        final LoginUser loginUser = new LoginUser();
        loginUser.setUsername(userDto.getEmail());
        loginUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        loginUser.setEnable(true);

        LoginUser user = (LoginUser) this.loginUserService.saveUser(loginUser);

        return "ok";
    }

}
