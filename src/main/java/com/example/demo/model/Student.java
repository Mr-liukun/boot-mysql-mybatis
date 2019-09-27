package com.example.demo.model;


import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Student implements Serializable {

    // alt+enter
    private static final long serialVersionUID = 1388594774613074515L;

    private Integer id;

    private String name;

    private float score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {

        this.score = score;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
