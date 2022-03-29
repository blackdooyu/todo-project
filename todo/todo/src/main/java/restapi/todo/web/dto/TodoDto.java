package restapi.todo.web.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import restapi.todo.domain.entity.todo.TodoState;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TodoDto {

    @NotEmpty
    private Long id;
    private String todoName;
    private TodoState todoState;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    public TodoDto(Long id, String todoName, TodoState todoState,LocalDate localDate) {
        this.id = id;
        this.todoName = todoName;
        this.todoState = todoState;
        this.localDate = localDate;
    }

    public TodoDto() {
    }
}
