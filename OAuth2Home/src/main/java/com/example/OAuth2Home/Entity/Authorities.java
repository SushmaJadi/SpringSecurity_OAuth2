package com.example.OAuth2Home.Entity;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

//@Component
@Entity
@Table(name = "authorities")
@IdClass(Authorities.class)
public class Authorities implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int aid;
    @Column(name = "userName")
    private String homeUserUserName;
    @Column(name = "authority")
    private String authority;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getHomeUserUserName() {
        return homeUserUserName;
    }

    public void setHomeUserUserName(String homeUserUserName) {
        this.homeUserUserName = homeUserUserName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public HomeUserEntityClass getHomeUserEntityClass() {
        return homeUserEntityClass;
    }

    public void setHomeUserEntityClass(HomeUserEntityClass homeUserEntityClass) {
        this.homeUserEntityClass = homeUserEntityClass;
    }

    @Autowired
    private HomeUserEntityClass homeUserEntityClass;

    @Override
    public String toString() {
        return "Authorities{" +
                "aid=" + aid +
                ", homeUserUserName='" + homeUserUserName + '\'' +
                ", authority='" + authority + '\'' +
                ", homeUserEntityClass=" + homeUserEntityClass +
                '}';
    }
}
