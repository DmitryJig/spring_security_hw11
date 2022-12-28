package com.spring.security.hw11.dto;

import com.spring.security.hw11.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserShortDto {
    private Long id;
    private String username;

    public UserShortDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
