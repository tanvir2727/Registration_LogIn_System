package com.AssignmentProject.AssignmentProject.Repository;

import com.AssignmentProject.AssignmentProject.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInforepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByEmail(String email);

}
