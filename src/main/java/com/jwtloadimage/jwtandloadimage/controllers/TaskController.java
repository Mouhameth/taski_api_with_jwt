package com.jwtloadimage.jwtandloadimage.controllers;

import com.jwtloadimage.jwtandloadimage.entities.Task;
import com.jwtloadimage.jwtandloadimage.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private  final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        return  ResponseEntity.ok().body(taskService.addTask(task));

    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task){
        return  ResponseEntity.ok().body(taskService.updateTask(task));

    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable("id") Long id){
        taskService.deleteTask(id);
    }

    @GetMapping("/user/tasks/{id}")
    public ResponseEntity<List<Task>> getAllTasksByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(taskService.getTaskByUserId(id));
    }

}
