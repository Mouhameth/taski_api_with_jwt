package com.jwtloadimage.jwtandloadimage.services;

import com.jwtloadimage.jwtandloadimage.entities.Task;
import com.jwtloadimage.jwtandloadimage.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    public Task updateTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public void deleteTask(Long id){
         taskRepository.deleteById(id);
    }

    public List<Task> getTaskByUserId(Long id){
        return taskRepository.findByUserId(id);
    }
}
