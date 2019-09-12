package app.pooi.authserver.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity(name = "t_user")
public class LoginUser {
    @Id
    String username;

    String password;

    boolean enable;

    LocalDateTime lastLoginTime;
}
