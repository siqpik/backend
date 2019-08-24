package com.example.siqpik;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "admirers")
public class Admirer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(unique = true)
    private Long id;

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
