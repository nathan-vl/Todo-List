package br.com.firma.todolistbackend.config;

import br.com.firma.todolistbackend.entity.Task;
import br.com.firma.todolistbackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Autowired
    TaskRepository taskRepository;

    @Bean
    public void criarEntidades() {
        Task task1 = new Task("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ultricies laoreet porta. Integer a diam sed orci finibus mattis. Suspendisse mollis ante et odio condimentum, sed interdum sem lobortis.", false);
        Task task2 = new Task("Lorem ipsum dolor sit amet", true);

        taskRepository.save(task1);
        taskRepository.save(task2);
    }
}
