package app.pooi.authserver.infrastructure.user;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Accessors(chain = true)
@Data
@Entity(name = "t_user")
public class User {
    @Id
    String username;

    String password;

    boolean enable;

    LocalDateTime lastLoginTime;
}
