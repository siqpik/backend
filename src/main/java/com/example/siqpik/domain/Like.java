package com.example.siqpik.domain;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pic_id")
    private Photo pic;

    public Like(User user, Photo pic) {
        this.user = user;
        this.pic = pic;
    }

    public User getUser() {
        return user;
    }

    public Photo getPic() {
        return pic;
    }
}
