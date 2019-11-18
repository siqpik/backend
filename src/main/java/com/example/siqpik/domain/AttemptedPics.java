package com.example.siqpik.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name= "attemptedPics")
public class AttemptedPics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Short attempts;

    @Column
    private LocalDate date;

    public AttemptedPics(User user) {
        this.user = user;
        this.attempts = 1;
        this.date = LocalDate.now(ZoneId.of("GMT"));
    }

    public Short getAttempts() {
        return attempts;
    }

    public LocalDate getDate() {
        return date;
    }

    public void addAttempt() {
        this.attempts++;
    }
}
