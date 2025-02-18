package com.AssignmentProject.AssignmentProject.Repository;

import com.AssignmentProject.AssignmentProject.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByAssignedUserEmail(String email);
}
