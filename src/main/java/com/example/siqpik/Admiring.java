package com.example.siqpik;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name ="admirings")

public class Admiring {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_id")
    private User admiring;

    public Admiring () {}

    public Admiring (User user, User admiring) {
        this.user = user;
        this.admiring = admiring;
    }
}
