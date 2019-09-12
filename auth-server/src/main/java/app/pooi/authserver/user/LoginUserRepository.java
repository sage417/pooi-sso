package app.pooi.authserver.user;

import org.springframework.data.repository.CrudRepository;

//@Repository
interface LoginUserRepository extends CrudRepository<LoginUser, String> {

}
