package com.example.OAuth2Home.service;


import com.example.OAuth2Home.Entity.Authorities;
import com.example.OAuth2Home.Entity.HomeUserEntityClass;
import com.example.OAuth2Home.repository.AuthoritiesRepo;
import com.example.OAuth2Home.repository.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {


  private UserRepository userRepo;

  private AuthoritiesRepo authoritiesRepo;

    public UserService(){

    }

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public UserService(AuthoritiesRepo authoritiesRepo){
        this.authoritiesRepo = authoritiesRepo;
    }
    @Query("select *from home_user where userName=?1")
    public HomeUserEntityClass getAllUsersById(String userName){
        Optional<HomeUserEntityClass> user = userRepo.findById(userName);
       System.out.println(user.get());
        return user.get();
    }

    @Query("select *from authorities where userName=?1")
    public Authorities getAllUsersById(Integer userName){
        Optional<Authorities> user = authoritiesRepo.findById(userName);
        System.out.println(user.get());
        return user.get();
    }
    public Authorities getUserDetails(Integer id){

        return  authoritiesRepo.findById(id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        HomeUserEntityClass homeUser = userRepo.findById(userName).orElseThrow(() -> new UsernameNotFoundException(userName + "not found"));

        List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(homeUser.getRole()));
        return new User(homeUser.getUsername(),homeUser.getPassword(),grantedAuthorities);
    }


}
