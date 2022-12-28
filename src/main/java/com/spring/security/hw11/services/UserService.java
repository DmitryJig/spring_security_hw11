package com.spring.security.hw11.services;

import com.spring.security.hw11.dto.UserDtoForSave;
import com.spring.security.hw11.dto.UserShortDto;
import com.spring.security.hw11.model.Role;
import com.spring.security.hw11.model.User;
import com.spring.security.hw11.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    private void init() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public List<UserShortDto> findAllUsers() {
        return userRepository.findAll().stream().map(p -> new UserShortDto(p)).toList();
    }


    public Optional<UserShortDto> findUserById(Long id) {
        return userRepository.findById(id).map(UserShortDto::new);
    }

    public List<User> findAllFullUsers() {
        return userRepository.findAll(); // TODO delete
    }

    public User saveNewUser(UserDtoForSave userDtoForSave) {
            User user = new User();
            user.setUsername(userDtoForSave.getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(userDtoForSave.getPassword()));
            user.setEmail(userDtoForSave.getEmail());
            user.setRoles(userDtoForSave.getRoles());
            return userRepository.save(user);
    }

    @Transactional
    public User updateRoles(UserDtoForSave userDtoForSave){
        Collection<Role> roles = userDtoForSave.getRoles();
        Long id = userDtoForSave.getId();
        User user = userRepository.findById(id).get();
        user.setRoles(roles);
        return user;
    }
}
