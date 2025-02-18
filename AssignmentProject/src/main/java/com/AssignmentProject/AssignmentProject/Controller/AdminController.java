package com.AssignmentProject.AssignmentProject.Controller;

import com.AssignmentProject.AssignmentProject.Dto.TaskDto;
import com.AssignmentProject.AssignmentProject.Entity.Task;
import com.AssignmentProject.AssignmentProject.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/task")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/task/create")
    public String createTask(@RequestBody TaskDto task, @RequestParam List<String> userEmails) {
        return taskService.createTask(task, userEmails);
    }

    @PutMapping("/task/update/{taskId}")
    public String updateTask(@PathVariable Long taskId, @RequestParam String status) {
        return taskService.updateTask(taskId, status);
    }

    @DeleteMapping("/task/delete/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        return taskService.deleteTask(taskId);
    }

    @PostMapping("/task/assign/{taskId}")
    public String assignUsersToTask(@PathVariable Long taskId, @RequestParam List<String> userEmails) {
        return taskService.assignUsersToTask(taskId, userEmails);
    }

    @GetMapping("/task/all")
    public List<Task> getAllTasks() {
        return taskService.getUserTasks();
    }
}
