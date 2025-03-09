package com.sitru.SpringSecurityDemo.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.sitru.SpringSecurityDemo.Model.UserPrincipal;
import com.sitru.SpringSecurityDemo.Model.Users;
import com.sitru.SpringSecurityDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(username);

        if(user == null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found ex");
        }
        return new UserPrincipal(user);
    }
}
