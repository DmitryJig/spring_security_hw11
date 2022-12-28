package com.spring.security.hw11.controllers;

import com.spring.security.hw11.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping
    public String pageForAuthUsers(Principal principal){
        System.out.println(principal.getName());
        return "Welcome " + principal.getName();
    }
}
