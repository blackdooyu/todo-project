package restapi.todo.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import restapi.todo.domain.entity.todo.TodoState;


@Data
public class TodoDto {


    private Long id;
    private String todoName;
    private TodoState todoState;
    private String localDate;

    @QueryProjection
    public TodoDto(Long id, String todoName, TodoState todoState,String localDate) {
        this.id = id;
        this.todoName = todoName;
        this.todoState = todoState;
        this.localDate = localDate;
    }

    public TodoDto() {
    }
}
