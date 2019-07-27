package com.example.siqpik;


import javax.persistence.*;

@Entity
@Table(name ="admirings")

public class Admiring {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_id")
    private User admiring;

    /******************************************************
     *          Constructors
     *****************************************************/

    public Admiring () {}

    public Admiring (User user, User admiring) {
        this.user = user;
        this.admiring = admiring;
    }
}
