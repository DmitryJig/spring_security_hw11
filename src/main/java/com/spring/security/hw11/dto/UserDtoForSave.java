package com.spring.security.hw11.dto;

import com.spring.security.hw11.model.Role;
import com.spring.security.hw11.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoForSave {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Collection<Role> roles;

    public UserDtoForSave(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}
