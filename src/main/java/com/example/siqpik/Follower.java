package com.example.siqpik;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_id")
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
