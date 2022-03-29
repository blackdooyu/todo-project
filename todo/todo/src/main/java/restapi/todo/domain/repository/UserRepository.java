package restapi.todo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restapi.todo.domain.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

}
