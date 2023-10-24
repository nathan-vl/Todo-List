package br.com.firma.todolistbackend.repository;

import br.com.firma.todolistbackend.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
