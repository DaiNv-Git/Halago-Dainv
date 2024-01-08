package com.example.halagodainv.config.userconfig;

import com.example.halagodainv.model.UserEntity;
import com.example.halagodainv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@SuppressWarnings("deprecation")
public class UserAuthenLogin {
    @Autowired
    private UserRepository userRepository;
    public String getUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(u -> {
                    if (u instanceof User) return ((User) u).getUsername();
                    else return null;
                }).orElse(null);
    }

    public Optional<UserEntity> getUserLogin() {
        String userName = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(u -> {
                    if (u instanceof User) return ((User) u).getUsername();
                    else return null;
                }).orElse(null);
        Optional<UserEntity> userEntity = userRepository.findByEmailOrUserName(userName, userName);
        return userEntity;
    }
}
