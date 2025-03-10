package com.sitru.SpringSecurityDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

/*
         // every request is authenticated here
          httpSecurity.csrf(customizer-> customizer.disable());
          httpSecurity.authorizeHttpRequests(request-> request.anyRequest().authenticated());
          httpSecurity.formLogin(Customizer.withDefaults());
          httpSecurity.httpBasic(Customizer.withDefaults());
          httpSecurity.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
          return httpSecurity.build();*/


      return   httpSecurity
                           .csrf(customizer->customizer.disable())
                           .authorizeHttpRequests( request-> request.requestMatchers("register","login")
                                   .permitAll()
                                   .requestMatchers("students")
                                   .permitAll()
                                   .anyRequest()
                                   .authenticated())
                           .httpBasic(Customizer.withDefaults())
                           .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                           .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                           .build();


    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //with out creating BCrypt we can use this
       // provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

   /* @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("sit")
                .password("ss3tur")
                .roles("USER")
                .build();
        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("sitru")
                .password("s@8106")
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user2,user1);
    }*/

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();

    }
}
