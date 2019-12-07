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
    @JoinColumn(name = "admirer_id")
    private User admirer;                       //The user who admires User1

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User admired;                      //User1

    /******************************************************
     *          Constructors
     *****************************************************/

    public Admirer(){}

    public Admirer(User admirer, User admired) {
        this.admirer = admirer;
        this.admired = admired;
        this.date = LocalDateTime.now(ZoneId.of("GMT"));
    }

    /******************************************************
     *          Getters & Setters
     *****************************************************/

    public Long getId() {
        return id;
    }

    public User getAdmired() {
        return admired;
    }

    public User getAdmirer() {
        return admirer;
    }
}
