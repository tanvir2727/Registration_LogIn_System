//package com.AssignmentProject.AssignmentProject.Service;
//
//import com.AssignmentProject.AssignmentProject.Entity.Task;
//import com.AssignmentProject.AssignmentProject.Entity.UserInfo;
//import com.AssignmentProject.AssignmentProject.Repository.TaskRepository;
//import com.AssignmentProject.AssignmentProject.Repository.UserInforepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AdminTaskService {
//
//    @Autowired
//    private TaskRepository taskRepository;
//
//    @Autowired
//    private UserInforepository userInforepository;
//
//
//
//    public List<Task> getAllTasks() {
//        return taskRepository.findAll();
//    }
//
//    public String updateTaskStatus(Long taskId,String status) {
//        Optional<Task> task = taskRepository.findById(taskId);
//        if(task.isPresent()) {
//            task.get().setStatus(status);
//            taskRepository.save(task.get());
//            return "Task status Updated";
//        }else{
//            return "Task Not Found";
//        }
//    }
//
//    public void deleteTask(Long taskId) {
//        taskRepository.deleteById(taskId);
//    }
//
//}
