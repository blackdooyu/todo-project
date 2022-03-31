package restapi.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import restapi.todo.domain.entity.User;
import restapi.todo.domain.entity.todo.Todo;
import restapi.todo.domain.entity.todo.TodoState;
import restapi.todo.domain.repository.UserRepository;
import restapi.todo.domain.service.TodoService;
import restapi.todo.web.dto.TodoDto;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TodoTest {

    @Autowired
    TodoService todoService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    @Test
    void 저장_테스트() {
        //given
        User user = User.createUser("test@emai.com", "테스트");

        userRepository.save(user);
        todoService.todoSave(user.getId(),"Todo","2022-03-31");

        em.flush();
        em.clear();

        //when
        System.out.println("==================");
        Todo findTodo = em.createQuery("select t from Todo t join fetch t.user where t.user.id =:id", Todo.class)
                .setParameter("id", user.getId())
                .getSingleResult();

        User findUser = findTodo.getUser();

        //then
        assertEquals(findTodo.getTodoName(),"Todo");
        assertEquals(findUser.getEmail(),"test@emai.com");
    }

    @Test
    void 업데이트_테스트() {
        //given
        User user = User.createUser("test@emai.com", "테스트");
        Todo todo = Todo.createTodo("Todo", "2022-03-31");

        userRepository.save(user);
        todo.insertUser(user);

        em.persist(todo);
        em.flush();
        em.clear();

        //when
        todoService.todoStateUpdate(user.getId(),todo.getId());
        Todo findTodo = em.createQuery("select t from Todo t where t.id = :id", Todo.class)
                .setParameter("id", todo.getId())
                .getSingleResult();

        //then
        assertEquals(findTodo.getTodoState(), TodoState.Y);

    }

    @Test
    void 삭제_테스트() {
        //given
        User user = User.createUser("test@emai.com", "테스트");
        Todo todo = Todo.createTodo("Todo", "2022-03-31");

        userRepository.save(user);
        todo.insertUser(user);

        em.persist(todo);
        em.flush();
        em.clear();

        //when
        todoService.deleteTodo(user.getId(),todo.getId());
        List<Todo> todoList = em.createQuery("select t from Todo t where t.id = :id", Todo.class)
                .setParameter("id", todo.getId())
                .getResultList();

        //then
        assertEquals(todoList.isEmpty(),true);

    }

    @Test
    void 날짜기준_조회_테스트() {
        //given
        User user = User.createUser("test@emai.com", "테스트");
        Todo todo = Todo.createTodo("Todo", "2022-03-31");

        userRepository.save(user);
        todo.insertUser(user);

        em.persist(todo);
        em.flush();
        em.clear();

        //when
        List<TodoDto> findTodo = todoService.findByList(user.getId(), "2022-03-31");

        //then
        assertEquals(findTodo.get(0).getLocalDate(),"2022-03-31");

    }
}
