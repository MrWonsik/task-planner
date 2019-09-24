package com.wasacz.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/addTask")
    void addTask(){
        Task testTask = new Task("Simply task");
        taskRepository.save(testTask);
    }
}
