package restapi.todo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import restapi.todo.domain.entity.todo.Todo;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    @Query("select t from Todo t where t.user.id = :id")
    List<Todo> findListById(@Param("id") Long id);

    @Query("select t from Todo t where t.user.id =:userId and t.id =:todoId")
    Todo findUserTodo(@Param("userId") Long userId , @Param("todoId") Long todoId);

}
