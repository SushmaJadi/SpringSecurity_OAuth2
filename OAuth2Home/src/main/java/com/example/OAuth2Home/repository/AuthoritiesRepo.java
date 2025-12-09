package com.example.OAuth2Home.repository;

import com.example.OAuth2Home.Entity.Authorities;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Scope("prototype")
public abstract class AuthoritiesRepo implements JpaRepository<Authorities, Integer> {

    @Override
   public Optional<Authorities> findById(Integer integer){
        return Optional.empty();
    };
}
