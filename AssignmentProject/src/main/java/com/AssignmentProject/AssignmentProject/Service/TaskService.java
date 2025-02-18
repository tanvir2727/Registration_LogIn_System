package com.AssignmentProject.AssignmentProject.Service;

import com.AssignmentProject.AssignmentProject.Dto.TaskDto;
import com.AssignmentProject.AssignmentProject.Entity.Task;
import com.AssignmentProject.AssignmentProject.Entity.UserInfo;
import com.AssignmentProject.AssignmentProject.Repository.TaskRepository;
import com.AssignmentProject.AssignmentProject.Repository.UserInforepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserInforepository userInforepository;

    // Create a task (Both User & Admin can create)
    public String createTask(TaskDto taskDto, List<String> userEmails) {
        String loggedInUser = getLoggedInUserEmail();
        if (loggedInUser == null) return "User is not authenticated";

        Task task = Task.builder()
                .id(taskDto.id())
                .description(taskDto.description())
                .title(taskDto.title())
                .build();

        Set<UserInfo> users = new HashSet<>();
        for (String email : userEmails) {
            Optional<UserInfo> user = userInforepository.findByEmail(email);
            user.ifPresent(users::add);
        }

        if (users.isEmpty()) return "No valid users found!";

        task.setCreatedBy(loggedInUser);  // Store the creator's email
        task.setAssignedUsers(users);
        task.setStatus("PENDING");
        taskRepository.save(task);
        return "Task Created Successfully!";
    }

    // Get tasks for the logged-in user
    public List<Task> getUserTasks() {
        String email = getLoggedInUserEmail();
        return taskRepository.findByAssignedUserEmail(email);
    }

    // Update Task (Only creator can update)
    public String updateTask(Long taskId, String status) {
        String loggedInUser = getLoggedInUserEmail();
        Optional<Task> task = taskRepository.findById(taskId);

        if (task.isPresent()) {
            if (!task.get().getCreatedBy().equals(loggedInUser)) {
                return "Only the creator can update this task!";
            }
            task.get().setStatus(status);
            taskRepository.save(task.get());
            return "Task updated successfully!";
        }
        return "Task not found!";
    }

    // Delete Task (Only creator can delete)
    public String deleteTask(Long taskId) {
        String loggedInUser = getLoggedInUserEmail();
        Optional<Task> task = taskRepository.findById(taskId);

        if (task.isPresent()) {
            if (!task.get().getCreatedBy().equals(loggedInUser)) {
                return "Only the creator can delete this task!";
            }
            taskRepository.deleteById(taskId);
            return "Task deleted successfully!";
        }
        return "Task not found!";
    }

    // Assign more users to a task (Only creator can assign)
    public String assignUsersToTask(Long taskId, List<String> userEmails) {
        String loggedInUser = getLoggedInUserEmail();
        Optional<Task> task = taskRepository.findById(taskId);

        if (task.isPresent()) {
            if (!task.get().getCreatedBy().equals(loggedInUser)) {
                return "Only the creator can assign members to this task!";
            }

            Set<UserInfo> users = new HashSet<>(task.get().getAssignedUsers());

            for (String email : userEmails) {
                Optional<UserInfo> user = userInforepository.findByEmail(email);
                user.ifPresent(users::add);
            }

            task.get().setAssignedUsers(users);
            taskRepository.save(task.get());
            return "Users assigned successfully!";
        }
        return "Task not found!";
    }

    // Get Logged-in User Email
    private String getLoggedInUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }
}
