package com.example.OAuth2Home.repository;

import com.example.OAuth2Home.Entity.HomeUserEntityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;




@Repository
@Scope("prototype")
public abstract class UserRepository implements JpaRepository<HomeUserEntityClass, String> {

    @Autowired
    HomeUserEntityClass homeUserEntityClass;

    @Override
    public <S extends HomeUserEntityClass> List<S> findAll(Example<S> example) {
        return Optional.ofNullable(example).map(e -> List.of(e.getProbe())).orElse(null);
    }




    @Query("select *from users where userName=?1")
    @Override
    public Optional<HomeUserEntityClass> findById(String s) {
        return Optional.of(s).map(id -> {
            homeUserEntityClass.setUsername(id);
            return Optional.of(homeUserEntityClass);
        }).orElse(null);


    }
}
