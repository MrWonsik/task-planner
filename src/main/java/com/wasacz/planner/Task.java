package com.wasacz.planner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Service
public class Task {

    @Id
    @GeneratedValue
    private int id;

    private String nameOfTask;
    private boolean isMade = false;

    public Task(String nameOfTask) {
        this.nameOfTask = nameOfTask;
    }
}