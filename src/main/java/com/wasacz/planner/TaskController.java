package com.wasacz.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/addTask")
    public ResponseEntity addTask(
            @RequestBody String descriptionOfTask
    ){
        Task newTask = new Task(descriptionOfTask);
        taskRepository.save(newTask);
        return ResponseEntity.ok(newTask);
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity getAllTasks(){
        List<Task> all = taskRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
