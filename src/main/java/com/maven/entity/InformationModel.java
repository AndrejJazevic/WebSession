package com.maven.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Clob;

@Entity
public class InformationModel implements EntityWithId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "text",nullable = false)
    private String text;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSession() {
        return user;
    }

    public void setUser(User u) {
        this.user = u;
    }
}
