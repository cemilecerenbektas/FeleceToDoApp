package basic.todo.app.dto;

import basic.todo.app.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUpdateToDo {
    private String content;
    private String author;
    private Status status;
}
