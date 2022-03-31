package restapi.todo.domain.repository;

import restapi.todo.web.dto.TodoDto;

import java.util.List;

public interface TodoRepositoryCustom {

  List<TodoDto> getTodoDtoList(Long userId,String date);
}
