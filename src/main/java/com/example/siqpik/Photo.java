package com.example.siqpik;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private byte[] pic;

    @Transient
    private Map picInfo;

    @OneToMany(mappedBy = "photo")
    List<Tag> tags = new LinkedList<>();

    /******************************************************
     *          Constructors
     *****************************************************/

    public Photo(){}

    public Photo(byte[] pic, Map picInfo) {
        this.pic = pic;
        this.picInfo = picInfo;
    }

    /******************************************************
     *          Getters & Setters
     *****************************************************/

    public User getUser() {
        return user;
    }
}
