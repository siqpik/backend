package com.example.siqpik;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Map;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private byte[] pic;

    private Map picInfo;

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
