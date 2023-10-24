package br.com.firma.todolistbackend.dto;

import br.com.firma.todolistbackend.entity.Task;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskDTO {
    private Long id;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    private Boolean done;

    public Task toTask() {
        Task task = new Task();
        task.setId(id);
        task.setDescription(description);
        task.setDone(done);

        return task;
    }
}
