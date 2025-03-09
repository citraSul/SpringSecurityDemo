package com.sitru.SpringSecurityDemo.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
@GetMapping("/")
    public String greet(HttpServletRequest request){


    return "Hello Sitra's Session when loggedIn  " + request.getSession().getId();

    }
}
