package com.shompme.admin.user.service;

import com.shompme.admin.user.exception.UserNotFoundException;
import com.shompme.admin.user.repository.RoleRepository;
import com.shompme.admin.user.repository.UserRepository;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        encodePassword(user);
        userRepository.save(user);
    }

    @Override
    public boolean isEmailUnique(String email) {
        User userEmail = userRepository.getUserByEmail(email);

        return userEmail == null;
    }

    @Override
    public User editUser(Integer id) throws UserNotFoundException {
        try {
            return userRepository.findById(id).get();
        }catch (NoSuchElementException e){
            throw  new UserNotFoundException("could not found any user with id : "+ id);
        }
    }

    private void encodePassword(User user){
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
    }

}
