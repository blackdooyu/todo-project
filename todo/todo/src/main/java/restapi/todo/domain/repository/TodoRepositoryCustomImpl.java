package restapi.todo.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import restapi.todo.web.dto.QTodoDto;
import restapi.todo.web.dto.TodoDto;

import java.util.List;

import static restapi.todo.domain.entity.todo.QTodo.*;


@RequiredArgsConstructor
public class TodoRepositoryCustomImpl implements TodoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 해당 날짜의 TodoDto 가져오기
     */
    @Override
    public List<TodoDto> getTodoDtoList(Long userId, String date) {
        return queryFactory
                .select(new QTodoDto(
                        todo.id,
                        todo.todoName,
                        todo.todoState,
                        todo.todoDate
                ))
                .from(todo)
                .where(todo.user.id.eq(userId).and(dateEq(date)))
                .fetch();
    }

    private BooleanExpression dateEq(String date) {
        return date != null ? todo.todoDate.eq(date) : null;
    }
}
