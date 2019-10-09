package com.wasacz.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://192.168.0.127:7777")
@RestController
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
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/getMadeTasks")
    public ResponseEntity getMadeTasks() {
        List<Task> madeTasks = taskRepository.findTasksByIsMadeOrderByDateOfAddDesc(true);
        return ResponseEntity.ok(madeTasks);
    }

    @GetMapping("/getTasksToDo")
    public ResponseEntity getTasksToDo() {
        List<Task> tasksToDo = taskRepository.findTasksByIsMadeOrderByDateOfAddDesc(false);
        return ResponseEntity.ok(tasksToDo);
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
            taskToMarkAsComplete.get().setDateOfComplete(LocalDateTime.now());
            taskRepository.save(taskToMarkAsComplete.get());
            return ResponseEntity.ok(taskToMarkAsComplete.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/undoCompleteTask/{id}")
    public ResponseEntity undoCompleteTask(
            @PathVariable long id) {
        Optional<Task> taskMarkAsComplete= taskRepository.findById(id);
        if (taskMarkAsComplete.isPresent()) {
            taskMarkAsComplete.get().setMade(false);
            taskMarkAsComplete.get().setDateOfComplete(null);
            taskRepository.save(taskMarkAsComplete.get());
            return ResponseEntity.ok(taskMarkAsComplete.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}