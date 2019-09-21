package com.example.siqpik.domain;

import org.hibernate.type.EmbeddedComponentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_id")
    private Photo pic;

    @Column
    private LocalDateTime date;

    /******************************************************
     *          Constructors
     *****************************************************/

    public Tag() {}

    public Tag(User user, Photo pic) {
        this.user = user;
        this.pic = pic;
        this.date = LocalDateTime.now(ZoneId.of("GMT"));
    }

    public User getUser() {
        return user;
    }
}
