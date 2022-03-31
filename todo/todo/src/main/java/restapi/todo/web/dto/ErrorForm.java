package restapi.todo.web.dto;

import lombok.Getter;

@Getter
public class ErrorForm {

    private int status;
    private String message;

    public ErrorForm(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
