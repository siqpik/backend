package com.example.siqpik;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String userName;
    private String email;
    private String password;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Photo> photos = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private Set<Admirer> admirers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Admiring> admirings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    List<Tag> tags = new LinkedList<>();

    /******************************************************
     *          Constructors
     *****************************************************/

    public User(){}

    public User(String userName, String name, String email, String password) {
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /******************************************************
     *          Getters & Setters
     *****************************************************/

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

}
