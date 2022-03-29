package restapi.todo.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import restapi.todo.web.dto.ErrorForm;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorForm userEx(UserException userException) {
        return new ErrorForm(HttpServletResponse.SC_BAD_REQUEST,userException.getMessage());
    }
}
