package com.example.siqpik.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name ="admirings")

public class Admiring {

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
    @JoinColumn(name = "admiring_id")
    private User admiring;

    /******************************************************
     *          Constructors
     *****************************************************/

    public Admiring () {}

    public Admiring (User user, User admiring) {
        this.user = user;
        this.admiring = admiring;
        this.date = LocalDateTime.now(ZoneId.of("GMT"));
    }

    public Long getId() {
        return id;
    }

    public User getAdmiring() {
        return admiring;
    }
}
