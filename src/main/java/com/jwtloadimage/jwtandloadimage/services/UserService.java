package com.jwtloadimage.jwtandloadimage.services;

import com.jwtloadimage.jwtandloadimage.entities.Role;
import com.jwtloadimage.jwtandloadimage.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User addUser(User user) throws IOException;
    public Role addRole(Role role);
    public void addRoleToUser(String username,String roleName);
    public List<User> getAllUsers();
    public User getUser(String userName);
    public  void deleteUser(Long id);
}
