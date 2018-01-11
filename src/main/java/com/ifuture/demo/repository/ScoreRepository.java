package com.ifuture.demo.repository;

import com.ifuture.demo.domain.Score;
import com.ifuture.demo.service.dto.ScoreDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Score entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScoreRepository extends JpaRepository<Score, Long>,
    JpaSpecificationExecutor<Score> {

    @Query("SELECT a FROM Score a WHERE a.student.stuName=?1 AND a.course.cname=?2")
        //@Query(nativeQuery = true,value = "SELECT a.* FROM TB_SCORE a,TB_STUDENT b,TB_COURSE c WHERE a.student_id=b.id and a.course_id=c.id AND b.stu_name=?1 and c.cname=?2")
    List<Score> searchScores(String stuName, String cname);

    @Query("SELECT new com.ifuture.demo.service.dto.ScoreDTO(a.student.stuName,a.course.cname ,a.score )  FROM Score a WHERE a.student.stuName=?1 AND a.course.cname=?2")
    List<ScoreDTO> query4Scores(String stuName, String cname);
}
