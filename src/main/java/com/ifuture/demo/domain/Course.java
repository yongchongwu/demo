package com.ifuture.demo.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Course.
 */
@Entity
@Table(name = "tb_course")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "myGenerator")
    @GenericGenerator(name = "myGenerator", strategy = "com.ifuture.demo.repository.id.AssignedOrIdentityGenerator")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "cname", length = 50, nullable = false)
    private String cname;

    @NotNull
    @Column(name = "ctime", nullable = false)
    private Integer ctime;

    @NotNull
    @Column(name = "credit", nullable = false)
    private Double credit;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public Course cname(String cname) {
        this.cname = cname;
        return this;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getCtime() {
        return ctime;
    }

    public Course ctime(Integer ctime) {
        this.ctime = ctime;
        return this;
    }

    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    public Double getCredit() {
        return credit;
    }

    public Course credit(Double credit) {
        this.credit = credit;
        return this;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
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
        Course course = (Course) o;
        if (course.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), course.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", cname='" + getCname() + "'" +
            ", ctime='" + getCtime() + "'" +
            ", credit='" + getCredit() + "'" +
            "}";
    }
}
