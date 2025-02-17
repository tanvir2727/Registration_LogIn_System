package com.AssignmentProject.AssignmentProject.Service;

import com.AssignmentProject.AssignmentProject.Entity.UserInfo;
import com.AssignmentProject.AssignmentProject.Repository.UserInforepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInforepository userInforepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = userInforepository.findByEmail(username);
        return userDetail.map(UserInfoDetails::new).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username));
    }

    public String addUser(UserInfo userInfo) {

        if (userInfo.getPassword() == null || userInfo.getPassword().isEmpty()) {
            return "Password cannot be empty";
        }

        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInforepository.save(userInfo);
        return "User added successfully";
    }
}
