package com.example.siqpik.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "requests")
public class AdmireRequest {

    public static final String PENDING = "Pending";
    public static final String ACCEPTED = "Accepted";
    public static final String CANCELED = "Canceled";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @Column(updatable = false)
    private LocalDateTime creationDate;

    @Column
    private LocalDateTime responseDate;

    @Column
    private String status;

    public AdmireRequest() {}

    public AdmireRequest(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.creationDate = LocalDateTime.now(ZoneId.of("GMT"));
        this.responseDate = null;
        this.status = "Pending";
    }

    public void setResponseDate(LocalDateTime responseDate) {
        this.responseDate = responseDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getStatus() {
        return status;
    }
}
