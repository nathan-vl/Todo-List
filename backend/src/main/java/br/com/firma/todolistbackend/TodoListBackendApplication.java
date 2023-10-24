package br.com.firma.todolistbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("br.com.firma.todolistbackend.*")
@ComponentScan(basePackages = {"br.com.firma.todolistbackend.*"})
@EntityScan("br.com.firma.todolistbackend.*")
public class TodoListBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoListBackendApplication.class, args);
    }
}
