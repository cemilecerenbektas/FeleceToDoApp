package basic.todo.app.service;

import basic.todo.app.Util;
import basic.todo.app.dto.DtoCreateToDo;
import basic.todo.app.dto.DtoGetToDo;
import basic.todo.app.dto.DtoUpdateToDo;
import basic.todo.app.entity.ToDoEntity;
import basic.todo.app.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;
    private final Util util;

    public ToDoServiceImpl(ToDoRepository toDoRepository, Util util) {
        this.toDoRepository = toDoRepository;
        this.util = util;
    }

    @Override
    public List<DtoGetToDo> getAllToDos() {
        List<ToDoEntity> dbToDos = toDoRepository.findAll();

        return dbToDos.stream().sorted(Comparator.comparing(ToDoEntity::getTime)).map(toDoEntity ->
                new DtoGetToDo(
                        toDoEntity.getId(),
                        toDoEntity.getContent(),
                        toDoEntity.getAuthor(),
                        util.formatDate(toDoEntity.getTime()),
                        toDoEntity.getStatus()
                )).collect(Collectors.toList());
    }

    @Override
    public DtoGetToDo getToDo(UUID id) {
        ToDoEntity toDoEntity = toDoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));

        return new DtoGetToDo(
                toDoEntity.getId(),
                toDoEntity.getContent(),
                toDoEntity.getAuthor(),
                util.formatDate(toDoEntity.getTime()),
                toDoEntity.getStatus()
        );
    }

    @Override
    public void createToDo(DtoCreateToDo dtoCreateToDo) {
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setContent(dtoCreateToDo.getContent());
        toDoEntity.setAuthor(dtoCreateToDo.getAuthor());
        toDoEntity.setStatus(dtoCreateToDo.getStatus());
        toDoRepository.save(toDoEntity);
    }

    @Override
    public void updateToDo(DtoUpdateToDo dtoUpdateToDo, UUID id) {
        ToDoEntity toDoEntity = toDoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));

        toDoEntity.setContent(dtoUpdateToDo.getContent());
        toDoEntity.setAuthor(dtoUpdateToDo.getAuthor());
        toDoEntity.setStatus(dtoUpdateToDo.getStatus());
        toDoEntity.setTime(Instant.now());

        toDoRepository.save(toDoEntity);
    }

    @Override
    public void deleteToDo(UUID id) {
        ToDoEntity toDoEntity = toDoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));

        toDoRepository.delete(toDoEntity);
    }
}
