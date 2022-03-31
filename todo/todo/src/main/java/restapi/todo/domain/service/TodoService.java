package restapi.todo.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapi.todo.domain.entity.User;
import restapi.todo.domain.entity.todo.Todo;
import restapi.todo.domain.repository.TodoRepository;
import restapi.todo.domain.repository.UserRepository;
import restapi.todo.web.dto.TodoDto;
import restapi.todo.web.error.UserException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public void todoSave(Long userId, String todoName,String todoDate) {
        User findUser = userRepository.getById(userId);
        Todo todo = Todo.createTodo(todoName,todoDate);

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

    // 날짜기준 TodoDto List  반환
    public List<TodoDto> findByList(Long id,String date) {
      return todoRepository.getTodoDtoList(id,date);
    }

    /**
     * 해당 User가 가지고있는 Todo에 대한 요청이 아닐경우
     * 예외를 던진다.
     */
    private Todo todoCheck(Long userId, Long todoId) {
        Todo findTodo = todoRepository.findUserTodo(userId, todoId);
        if (findTodo == null) {
            throw new UserException("권한이 없습니다.");

        }
        return findTodo;
    }

}
