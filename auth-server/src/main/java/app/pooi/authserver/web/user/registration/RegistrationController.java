package app.pooi.authserver.web.user.registration;

import app.pooi.authserver.user.registration.EmailExistsException;
import app.pooi.authserver.user.registration.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class RegistrationController {

    @Autowired
    UserRegistrationService userRegistrationService;


    @RequestMapping(value = "/user/registration")
    public String registerUserAccount(@RequestBody @Valid RegisterUserDto registerUserDto, BindingResult errors) {

        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, getMessage(errors), null);
        }

        try {
            userRegistrationService.registerNewUserAccount(registerUserDto);
        } catch (EmailExistsException e) {
            errors.rejectValue("email", "message.regError");
        }

        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, getMessage(errors), null);
        }
        return "ok";
    }

    private String getMessage(BindingResult errors) {
        return errors.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
    }

}
