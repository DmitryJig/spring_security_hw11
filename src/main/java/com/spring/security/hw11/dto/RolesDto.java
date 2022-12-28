package com.spring.security.hw11.dto;

import com.spring.security.hw11.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolesDto {
    private List<Role> roles;
}
