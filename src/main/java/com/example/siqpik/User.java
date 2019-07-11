package com.example.siqpik;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String userName;
    private String email;
    private String password;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Photo> photos = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private Set<Follower> followers = new LinkedHashSet<>();

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

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public Set<Follower> getFriends() {
        return followers;
    }

    /******************************************************
     *          Methods
     *****************************************************/

    public void addFriend(Follower friend) {
        followers.add(friend);
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

    public List<Photo> getMyPhotos(){
        return photos.stream()
                .filter(photo -> photo.getUser().id.equals(id))
                .collect(toList());
    }

    public List<Photo> getOtherPhotos(){
        return photos.stream()
                .filter(photo -> !photo.getUser().id.equals(id))
                .collect(toList());
    }
}
