package com.wasacz.planner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private LocalDateTime dateOfAdd;
    private boolean isMade = false;
    private LocalDateTime dateOfComplete;


    public Task(String description) {
        this.description = description;
        this.dateOfAdd = LocalDateTime.now();
    }
}