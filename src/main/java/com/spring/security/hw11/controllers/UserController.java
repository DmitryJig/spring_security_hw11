package com.spring.security.hw11.controllers;

import com.spring.security.hw11.dto.UserDtoForSave;
import com.spring.security.hw11.dto.UserShortDto;
import com.spring.security.hw11.exceptions.ResourceNotFoundException;
import com.spring.security.hw11.model.User;
import com.spring.security.hw11.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserShortDto> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserShortDto findUserById(@PathVariable Long id) {
        return userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " doens't exist"));
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @GetMapping("/fullusers")
    public List<User> findAllFullUsers() {
        return userService.findAllFullUsers();
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @PostMapping
    public User saveNewUser(@RequestBody UserDtoForSave userDtoForSave) {
        return userService.saveNewUser(userDtoForSave);
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @PutMapping
    public void updateRoles(@RequestBody UserDtoForSave userDtoForSave) {
        userService.updateRoles(userDtoForSave);
    }
}
