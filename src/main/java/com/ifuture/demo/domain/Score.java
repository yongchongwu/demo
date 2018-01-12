package com.ifuture.demo.domain;

import com.ifuture.demo.service.dto.ScoreDTO;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Score.
 */
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = "ScoreDTO",
        classes = @ConstructorResult(
            targetClass = ScoreDTO.class,
            columns = {
                @ColumnResult(name = "stuName",type = String.class),
                @ColumnResult(name = "courseName",type = String.class),
                @ColumnResult(name = "score",type = Double.class)
            }
        )
    )
})
@Entity
@Table(name = "tb_score")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 成绩
     */
    @NotNull
    @ApiModelProperty(value = "成绩", required = true)
    @Column(name = "score", nullable = false)
    private Double score;

    @ManyToOne(optional = false)
    @NotNull
    private Student student;

    @ManyToOne(optional = false)
    @NotNull
    private Course course;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public Score score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public Score student(Student Student) {
        this.student = Student;
        return this;
    }

    public void setStudent(Student Student) {
        this.student = Student;
    }

    public Course getCourse() {
        return course;
    }

    public Score course(Course Course) {
        this.course = Course;
        return this;
    }

    public void setCourse(Course Course) {
        this.course = Course;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        if (score.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), score.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Score{" +
            "id=" + getId() +
            ", score='" + getScore() + "'" +
            "}";
    }
}
