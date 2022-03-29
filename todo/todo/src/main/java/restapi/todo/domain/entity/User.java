package restapi.todo.domain.entity;

import lombok.Getter;
import restapi.todo.domain.entity.todo.Todo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User extends DateTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(updatable = false)
    private String email;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Todo> todoList = new ArrayList<>();

    protected User() {

    }

    /**
     * 생성 편의 메서드
     */
    public static User createUser(String email,String name){
        User user = new User();
        user.email = email;
        user.name = name;

        return user;
    }
}
