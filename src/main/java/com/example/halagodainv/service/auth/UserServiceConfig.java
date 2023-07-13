package com.example.halagodainv.service.auth;

import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserServiceConfig implements UserDetailsService {
    @Autowired
    private UserRepository authJPARepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> login = authJPARepository.findByEmail(email);
        if (login.isEmpty()) {
            throw new UsernameNotFoundException("user name or password not exits");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new User(login.get().getEmail(), login.get().getPassword(), authorities);
    }

    public UserDetails loadByUserName(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> login = authJPARepository.findByUserName(userName);
        if (login.isEmpty()) {
            throw new UsernameNotFoundException("user name or password not exits");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new User(login.get().getEmail(), login.get().getPassword(), authorities);
    }

    public UserDetails loadUserById(int id) throws UsernameNotFoundException {
        Optional<UserEntity> login = authJPARepository.findById(id);
        if (login.isEmpty()) {
            throw new UsernameNotFoundException("user name or password not exits");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new User(login.get().getUserName(), login.get().getPassword(), authorities);
    }
}
