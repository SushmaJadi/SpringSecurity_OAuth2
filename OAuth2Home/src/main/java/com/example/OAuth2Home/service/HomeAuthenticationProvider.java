package com.example.OAuth2Home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class HomeAuthenticationProvider implements AuthenticationManager {
    @Autowired
    private UserService  userService;
    private PasswordEncoder passwordEncoder;
    //@Autowired
    private UserDetails userDetails;

    public HomeAuthenticationProvider (PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        userDetails = userService.loadUserByUsername(userName);
      //  userDetails = userService.getAllUsersById(userName)

        if(passwordEncoder.matches(pwd,userDetails.getPassword())){
            return  new UsernamePasswordAuthenticationToken(userDetails, pwd, userDetails.getAuthorities());
        }
        else {
            throw  new BadCredentialsException("Invalid password");
        }

    }

   /* @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }*/
}
