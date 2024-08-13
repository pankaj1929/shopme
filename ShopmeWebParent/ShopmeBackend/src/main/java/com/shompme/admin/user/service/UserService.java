package com.shompme.admin.user.service;

import com.shompme.admin.user.exception.UserNotFoundException;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

import java.util.List;

public interface UserService {
     List<User>listAll();
     List<Role>listRoles();
     void saveUser(User user);
      boolean isEmailUnique(String email);
     User editUser(Integer id) throws UserNotFoundException;
}
