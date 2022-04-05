package restapi.todo.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import restapi.todo.web.dto.SessionUser;
import restapi.todo.domain.service.TodoService;
import restapi.todo.web.dto.ResponseForm;
import restapi.todo.web.dto.TodoDto;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.util.StringUtils.*;

@RestController
@RequiredArgsConstructor
public class TodoApiController {

    private final TodoService todoService;
    private final HttpSession httpSession;



    @GetMapping("/todo")
    public List<TodoDto> getTodo(@RequestParam(required = false) String localDate) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (!hasText(localDate)){
            localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        return todoService.findByList(user.getId(), localDate);
    }

    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseForm saveTodo(@RequestBody TodoDto todoDto) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        String localDate = todoDto.getLocalDate();

        if (!hasText(localDate)){
            localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        todoService.todoSave(user.getId(),todoDto.getTodoName(),localDate);

        return new ResponseForm(HttpServletResponse.SC_CREATED,"성공");
    }

    @PutMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseForm todoUpdate(@RequestBody TodoDto todoDto) {
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
