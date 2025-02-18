//package com.AssignmentProject.AssignmentProject.Service;
//
//import com.AssignmentProject.AssignmentProject.Entity.Task;
//import com.AssignmentProject.AssignmentProject.Repository.TaskRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserTaskService {
//
//    @Autowired
//    private TaskRepository taskRepository;
//
//    public List<Task> getUserTasks() {
//        String email = getLoggedInUserEmail();
//        return taskRepository.findByAssignedUserEmail(email);
//    }
//
//    public String markTaskAsCompleted(Long taskId) {
//        Optional<Task> task = taskRepository.findById(taskId);
//        if (task.isPresent()) {
//            task.get().setStatus("Completed");
//            taskRepository.save(task.get());
//            return "Task completed";
//        }
//        else{
//            return "Task not completed";
//        }
//    }
//
//    private String getLoggedInUserEmail() {
//        Object principle = SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
//        if(principle instanceof UserDetails) {
//            return ((UserDetails)principle).getUsername();
//        }
//        return null;
//    }
//
//}
