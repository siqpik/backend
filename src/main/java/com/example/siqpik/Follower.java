package com.example.siqpik;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private User follower;

    /******************************************************
     *          Constructors
     *****************************************************/

    public Follower(){}

    public Follower(User user, User follower) {
        this.user = user;
        this.follower = follower;
    }

    /******************************************************
     *          Getters & Setters
     *****************************************************/

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public User getFollower() {
        return follower;
    }
}
