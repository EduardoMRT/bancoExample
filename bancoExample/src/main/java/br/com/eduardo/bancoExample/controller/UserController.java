package br.com.eduardo.bancoExample.controller;

import br.com.eduardo.bancoExample.domain.User;
import br.com.eduardo.bancoExample.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<User> listUsers(){
        return userService.listUsers();
    }

}
