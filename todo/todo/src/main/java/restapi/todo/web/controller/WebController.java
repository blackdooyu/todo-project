package restapi.todo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/todo-list")
    public String page() {
        return "/index.html";
    }
}
