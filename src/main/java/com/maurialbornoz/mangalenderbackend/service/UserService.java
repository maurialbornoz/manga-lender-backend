package com.maurialbornoz.mangalenderbackend.service;

import com.maurialbornoz.mangalenderbackend.entity.User;
import com.maurialbornoz.mangalenderbackend.requestmodels.UserRegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService extends UserDetailsService {
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    public User getUser(String email);
    public User createUser(UserRegisterRequest user);

    String confirmToken(String token);



}
