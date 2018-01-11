package com.ifuture.demo.service.dto;

import java.io.Serializable;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

public class ScoreDTO implements Serializable{

    private String stuName;
    private String courseName;
    private Double score;

    public ScoreDTO() {
        super();
    }

    public ScoreDTO(String stuName, String courseName, Double score) {
        super();
        this.stuName = stuName;
        this.courseName = courseName;
        this.score = score;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
