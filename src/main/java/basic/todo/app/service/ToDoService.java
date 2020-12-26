package basic.todo.app.service;

import basic.todo.app.dto.DtoCreateToDo;
import basic.todo.app.dto.DtoGetToDo;
import basic.todo.app.dto.DtoUpdateToDo;

import java.util.List;
import java.util.UUID;

public interface ToDoService {
    List<DtoGetToDo> getAllToDos();

    DtoGetToDo getToDo(UUID id);

    void createToDo(DtoCreateToDo dtoCreateToDo);

    void updateToDo(DtoUpdateToDo dtoUpdateToDo, UUID id);

    void deleteToDo(UUID id);
}
