package basic.todo.app.repository;

import basic.todo.app.entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoEntity, UUID> {
}
