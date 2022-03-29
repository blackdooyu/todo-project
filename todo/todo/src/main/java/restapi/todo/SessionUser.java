package restapi.todo;

import lombok.Data;
import restapi.todo.domain.entity.User;

@Data
public class SessionUser {
    private Long id;
    private String name;
    private String email;

    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
