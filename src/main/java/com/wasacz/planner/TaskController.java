package com.wasacz.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("planner")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/addTask")
    public ResponseEntity addTask(
            @RequestBody String descriptionOfTask
    ) {
        Task newTask = new Task(descriptionOfTask);
        taskRepository.save(newTask);
        return ResponseEntity.ok(newTask);
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity getAllTasks() {
        List<Task> allTasks = taskRepository.findAll();
        return (allTasks.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(allTasks);
    }


    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity deleteTask(@PathVariable long id) {
        Optional<Task> toDelete = taskRepository.findById(id);

        if (toDelete.isPresent()) {
            taskRepository.delete(toDelete.get());
            return ResponseEntity.ok(toDelete.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editTask/{id}")
    public ResponseEntity editTask(
            @RequestBody String newDescriptionOfTask,
            @PathVariable long id) {
        Optional<Task> taskToEdit = taskRepository.findById(id);

        if (taskToEdit.isPresent()) {
            taskToEdit.get().setDescription(newDescriptionOfTask);
            taskRepository.save(taskToEdit.get());
            return ResponseEntity.ok(taskToEdit.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/completeTask/{id}")
    public ResponseEntity markTaskAsComplete(
            @PathVariable long id) {
        Optional<Task> taskToMarkAsComplete = taskRepository.findById(id);
        if (taskToMarkAsComplete.isPresent()) {
            taskToMarkAsComplete.get().setMade(true);
            taskRepository.save(taskToMarkAsComplete.get());
            return ResponseEntity.ok(taskToMarkAsComplete.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}