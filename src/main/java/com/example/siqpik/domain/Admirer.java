package com.example.siqpik.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "admirers")
public class Admirer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admirer_id")
    private User admirer;

    /******************************************************
     *          Constructors
     *****************************************************/

    public Admirer(){}

    public Admirer(User user, User admirer) {
        this.user = user;
        this.admirer = admirer;
        this.date = LocalDateTime.now(ZoneId.of("GMT"));
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

    public User getAdmirer() {
        return admirer;
    }
}
