package com.todo.business;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "description", length = 1000)
    private String description;

    public Todo(String description) {
        createdAt = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        updatedAt = null;
        this.status = false;
        this.description = description;
    }

    public void Edit(Boolean status, String description) {
        updatedAt = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        this.status = status;
        this.description = description;
    }
}
