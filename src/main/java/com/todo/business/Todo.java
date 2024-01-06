package com.todo.business;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_todo")
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "description", length = 1000)
    private String description;

    public Todo() {
    }

    public Todo(String description) {
        createdAt = new Date();
        updatedAt = null;
        this.status = false;
        this.description = description;
    }

    public void Edit(Boolean status, String description) {
        updatedAt = new Date();
        this.status = status;
        this.description = description;
    }
}
