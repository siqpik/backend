package com.example.siqpik.domain;

import javax.persistence.*;

@Entity
@Table(name ="admirings")

public class Admiring {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admiring_id")
    private User admiring;

    /******************************************************
     *          Constructors
     *****************************************************/

    public Admiring () {}

    public Admiring (User user, User admiring) {
        this.user = user;
        this.admiring = admiring;
    }

    public Long getId() {
        return id;
    }

    public User getAdmiring() {
        return admiring;
    }
}
