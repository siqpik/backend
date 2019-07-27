package com.example.siqpik;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "photo")
    List<Tag> tags = new LinkedList<>();

    private byte[] pic;
    private Integer bytes;
    private String created;
    private String etag;
    private String format;
    private Integer height;
    private String originalFileName;
    private Boolean placeholder;
    private String publicID;
    private String resourceType;
    private String secureUrl;
    private String signature;
    private ArrayList tagsClouddinary;
    private String type;
    private String url;
    private Integer version;
    private Integer width;


    /******************************************************
     *          Constructors
     *****************************************************/

    public Photo(){}

    public Photo(User user, byte[] pic, Map cloudinaryInfo) {
        this.user = user;
        this.pic = pic;
        this.bytes = (Integer) cloudinaryInfo.get("bytes");
        this.created = (String) cloudinaryInfo.get("created_at");
        this.etag = (String) cloudinaryInfo.get("etag");
        this.format = (String) cloudinaryInfo.get("format");
        this.height = (Integer) cloudinaryInfo.get("height");
        this.originalFileName = (String) cloudinaryInfo.get("original_filename");
        this.placeholder = (Boolean) cloudinaryInfo.get("placeholder");
        this.publicID = (String) cloudinaryInfo.get("public_id");
        this.resourceType = (String) cloudinaryInfo.get("resource_type");
        this.secureUrl = (String) cloudinaryInfo.get("secure_url");
        this.signature = (String) cloudinaryInfo.get("signature");
        this.signature = (String) cloudinaryInfo.get("signature");
        this.tagsClouddinary = (ArrayList) cloudinaryInfo.get("tags");
        this.type = (String) cloudinaryInfo.get("type");
        this.url = (String) cloudinaryInfo.get("url");
        this.version = (Integer) cloudinaryInfo.get("version");
        this.width = (Integer) cloudinaryInfo.get("width");
    }

    /******************************************************
     *          Getters & Setters
     *****************************************************/

    public User getUser() {
        return user;
    }
}
