package com.shompme.admin.user.controller;

import com.shompme.admin.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserRestController {
    private UserService userService;

    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(@Param("email")String email){
        return userService.isEmailUnique(email) ? "OK" : "Duplicated";
    }
}
