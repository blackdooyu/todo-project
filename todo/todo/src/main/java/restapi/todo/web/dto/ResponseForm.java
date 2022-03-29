package restapi.todo.web.dto;

import lombok.Getter;

@Getter
public class ResponseForm {

    private int state;
    private String message;


    public ResponseForm(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public ResponseForm() {
    }
}
