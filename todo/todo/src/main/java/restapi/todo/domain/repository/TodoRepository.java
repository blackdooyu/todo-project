package restapi.todo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import restapi.todo.domain.entity.todo.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long>,TodoRepositoryCustom {

    // TodoEntity의 수정,삭제 요청시 해당 유저가 권한이 있는지 확인
    @Query("select t from Todo t where t.user.id =:userId and t.id =:todoId")
    Todo findUserTodo(@Param("userId") Long userId , @Param("todoId") Long todoId);

}
