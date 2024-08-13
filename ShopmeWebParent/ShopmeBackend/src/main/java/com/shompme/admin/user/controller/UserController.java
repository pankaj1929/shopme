package com.shompme.admin.user.controller;

import com.shompme.admin.user.exception.UserNotFoundException;
import com.shompme.admin.user.service.UserService;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/users")
    public String listAll(Model model){
        List<User>userList = userService.listAll();
        model.addAttribute("userList",userList);
        return "users";
    }
    @GetMapping("/users/new")
    public String newUser(Model model){
        List<Role> roleList = userService.listRoles();
        User user = new User();
        user.setEnabled(true);
        model.addAttribute("user",user);
        model.addAttribute("roleList",roleList);
        return "user_form";
    }
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        System.out.println(user);
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message","The user has been saved successfully.");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable(name = "id")Integer id,RedirectAttributes redirectAttributes,
    Model model)  {
        try {
            User user = userService.editUser(id);
            model.addAttribute("user",user);
            return "user_form";
        }catch (UserNotFoundException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }
}
