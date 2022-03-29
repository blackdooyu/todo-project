package restapi.todo.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import restapi.todo.SessionUser;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final HttpSession httpSession;

    @GetMapping("/user")
    public SessionUser getUser() {
        return (SessionUser) httpSession.getAttribute("user");
    }
}
