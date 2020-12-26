package basic.todo.app.dto;

import basic.todo.app.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoGetToDo {
    private UUID id;
    private String content;
    private String author;
    private String time;
    private Status status;
}
