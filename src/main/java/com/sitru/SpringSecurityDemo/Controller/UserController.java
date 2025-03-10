package com.sitru.SpringSecurityDemo.Controller;

import com.sitru.SpringSecurityDemo.Model.Users;
import com.sitru.SpringSecurityDemo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
@Autowired
   private UsersService usersService;
    @PostMapping("/register")
    public Users register(@RequestBody Users users){
        return usersService.register(users);

    }
    @PostMapping("/login")
    public String login(@RequestBody Users user){
      return usersService.verify(user);
    }
}
