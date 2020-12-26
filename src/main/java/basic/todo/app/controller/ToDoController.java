package basic.todo.app.controller;

import basic.todo.app.dto.DtoCreateToDo;
import basic.todo.app.dto.DtoGetToDo;
import basic.todo.app.dto.DtoUpdateToDo;
import basic.todo.app.service.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/todo")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/all")
    public String getAllToDos(Model model) {
        List<DtoGetToDo> allToDos = toDoService.getAllToDos();
        model.addAttribute("todos", allToDos);
        model.addAttribute("dtoUpdateToDo", new DtoUpdateToDo());
        return "index";
    }

    @GetMapping("/create")
    public String getCreateToDo(Model model) {
        model.addAttribute("dtoCreateToDo", new DtoCreateToDo());
        return "create";
    }

    @PostMapping("/create")
    public String postCreateToDo(DtoCreateToDo dtoCreateToDo) {
        toDoService.createToDo(dtoCreateToDo);
        return "redirect:/todo/all";
    }

    @GetMapping("/update/{id}")
    public String getUpdateToDo(@PathVariable UUID id, Model model) {
        DtoGetToDo dtoUpdateToDo = toDoService.getToDo(id);
        model.addAttribute("dtoUpdateToDo", dtoUpdateToDo);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String postUpdateToDo(@PathVariable UUID id, DtoUpdateToDo dtoUpdateToDo) {
        toDoService.updateToDo(dtoUpdateToDo, id);
        return "redirect:/todo/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteToDo(@PathVariable UUID id) {
        toDoService.deleteToDo(id);
        return "redirect:/todo/all";
    }

}
