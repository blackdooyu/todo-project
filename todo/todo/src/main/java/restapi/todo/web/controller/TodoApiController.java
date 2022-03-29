package restapi.todo.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import restapi.todo.SessionUser;
import restapi.todo.domain.service.TodoService;
import restapi.todo.web.dto.ResponseForm;
import restapi.todo.web.dto.TodoDto;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TodoApiController {

    private final TodoService todoService;
    private final HttpSession httpSession;

    @GetMapping("/todo")
    public List<TodoDto> getTodo() {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        return todoService.findByList(user.getId())
                .stream().map(todo -> new TodoDto(todo.getId(),
                        todo.getTodoName(), todo.getTodoState(),todo.getCreteDate()))
                .collect(Collectors.toList());
    }

    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseForm todoSave(@RequestBody TodoDto todoDto) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        todoService.todoSave(user.getId(),todoDto.getTodoName());

        return new ResponseForm(HttpServletResponse.SC_CREATED,"성공");
    }

    @PutMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseForm todoComplete(@RequestBody TodoDto todoDto) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        todoService.todoStateUpdate(user.getId(), todoDto.getId());

        return new ResponseForm(HttpServletResponse.SC_OK,"성공");
    }

    @DeleteMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseForm todoDelete(@RequestBody TodoDto todoDto ) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        todoService.deleteTodo(user.getId(),todoDto.getId());

        return new ResponseForm(HttpServletResponse.SC_OK,"성공");
    }


}
