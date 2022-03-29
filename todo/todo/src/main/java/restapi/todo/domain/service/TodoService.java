package restapi.todo.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapi.todo.domain.entity.User;
import restapi.todo.domain.entity.todo.Todo;
import restapi.todo.domain.repository.TodoRepository;
import restapi.todo.domain.repository.UserRepository;
import restapi.todo.web.error.UserException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public void todoSave(Long userId, String todoName) {
        User findUser = userRepository.getById(userId);
        Todo todo = Todo.createTodo(todoName);

        todo.insertUser(findUser);

        todoRepository.save(todo);
    }

    @Transactional
    public void todoStateUpdate(Long userId,Long todoId) {
        Todo findTodo = todoCheck(userId, todoId);
        findTodo.updateState();
    }

    @Transactional
    public void deleteTodo(Long userId, Long todoId) {
        Todo findTodo = todoCheck(userId, todoId);
        todoRepository.delete(findTodo);

    }

    public List<Todo> findByList(Long id) {
      return todoRepository.findListById(id);
    }

    private Todo todoCheck(Long userId, Long todoId) {
        Todo findTodo = todoRepository.findUserTodo(userId, todoId);
        if (findTodo == null) {
            throw new UserException("권한이 없습니다.");

        }
        return findTodo;
    }

}
