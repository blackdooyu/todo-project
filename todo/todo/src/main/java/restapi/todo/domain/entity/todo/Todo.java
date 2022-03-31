package restapi.todo.domain.entity.todo;

import lombok.Getter;
import restapi.todo.domain.entity.DateTime;
import restapi.todo.domain.entity.User;

import javax.persistence.*;


import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Todo extends DateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    private String todoName;

    private String todoDate;

    @Enumerated(EnumType.STRING)
    private TodoState todoState;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    protected Todo() {
    }

    /**
     * 생성 편의 메서드
     */
    public static Todo createTodo(String todoName,String todoDate) {
        Todo todo = new Todo();
        todo.todoName = todoName;
        todo.todoDate = todoDate;
        todo.todoState = TodoState.N;
        return todo;
    }

    /**
     * 연관관계 주입 편의 메서드
     */
    public void insertUser(User user) {
        this.user = user;
        user.getTodoList().add(this);
    }

    // State 변경
    public void updateState() {

        if (this.getTodoState() == TodoState.N){
            this.todoState = TodoState.Y;
        } else {
            this.todoState = TodoState.N;
        }

    }
}
