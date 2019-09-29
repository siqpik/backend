package com.example.siqpik.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "likes")
public class Like {

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
    @JoinColumn(name = "pic_id")
    private Photo pic;

    public Like(){}

    public Like(User user, Photo pic) {
        this.user = user;
        this.pic = pic;
        this.date = LocalDateTime.now(ZoneId.of("GMT"));
    }

    public User getUser() {
        return user;
    }

    public Photo getPic() {
        return pic;
    }
}
