package basic.todo.app.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoEntity {

    @Id
    @GeneratedValue
    @Setter(value = AccessLevel.PROTECTED)
    private UUID id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Instant time = Instant.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
