package com.ifuture.demo.service;

import com.ifuture.demo.domain.Score;
import com.ifuture.demo.repository.ScoreRepository;
import com.ifuture.demo.service.dto.ScoreDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Score.
 */
@Service
@Transactional
public class ScoreService {

    private final Logger log = LoggerFactory.getLogger(ScoreService.class);

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;// 注入EntityManager实例

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    /**
     * Save a score.
     *
     * @param score the entity to save
     * @return the persisted entity
     */
    public Score save(Score score) {
        log.debug("Request to save Score : {}", score);
        return scoreRepository.save(score);
    }

    /**
     * Get all the scores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Score> findAll(Pageable pageable) {
        log.debug("Request to get all Scores");
        return scoreRepository.findAll(pageable);
    }

    /**
     * Get one score by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Score findOne(Long id) {
        log.debug("Request to get Score : {}", id);
        return scoreRepository.findOne(id);
    }

    /**
     * Delete the  score by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Score : {}", id);
        scoreRepository.delete(id);
    }

    public List<Score> searchScores(String stuName, String cname) {
        return scoreRepository.searchScores(stuName, cname);
    }

    public List<Score> queryScores(String stuName, String cname) {
        return scoreRepository.findAll(new Specification<Score>() {
            @Override
            public Predicate toPredicate(Root<Score> root, CriteriaQuery<?> criteriaQuery,
                CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                if (StringUtils.isNotBlank(stuName)) {
                    predicates
                        .add(criteriaBuilder.equal(root.get("student").get("stuName"), stuName));
                }
                if (StringUtils.isNotBlank(cname)) {
                    predicates.add(criteriaBuilder.equal(root.get("course").get("cname"), cname));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    public List<Score> query2Scores(String stuName, String cname) {
        /*Query query = entityManager.createQuery(
            "select a FROM  Score a WHERE a.student.stuName=:stuName AND a.course.cname=:cname",
            Score.class);
          query.setParameter("stuName",stuName);
          query.setParameter("cname",cname);
        */
        Query query = entityManager.createNativeQuery(
            "SELECT a.* FROM TB_SCORE a,TB_STUDENT b,TB_COURSE c WHERE a.student_id=b.id and a.course_id=c.id AND b.stu_name=?1 and c.cname=?2",
            Score.class);
        query.setParameter(1, stuName);
        query.setParameter(2, cname);
        return query.getResultList();
    }

    public List<Score> query3Scores(String stuName, String cname) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();// 获取builder
        CriteriaQuery<Score> query = builder.createQuery(Score.class);
        Root<Score> root = query.from(Score.class);// 构建Root
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(stuName)) {
            predicates
                .add(builder.equal(root.get("student").get("stuName"), stuName));
        }
        if (StringUtils.isNotBlank(cname)) {
            predicates.add(builder.equal(root.get("course").get("cname"), cname));
        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(query).getResultList();
    }


    public List<ScoreDTO> query4Scores(String stuName, String cname) {
        List<ScoreDTO> list = scoreRepository.query4Scores(stuName, cname);
        return list;
    }

    public List<ScoreDTO> query5Scores(String stuName, String cname) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();// 获取builder
        CriteriaQuery<ScoreDTO> query = builder.createQuery(ScoreDTO.class);
        Root<Score> root = query.from(Score.class);// 构建Root
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(stuName)) {
            predicates
                .add(builder.equal(root.get("student").get("stuName"), stuName));
        }
        if (StringUtils.isNotBlank(cname)) {
            predicates.add(builder.equal(root.get("course").get("cname"), cname));
        }
        query.multiselect(root.get("student").get("stuName"), root.get("course").get("cname"),
            root.get("score")).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(query).getResultList();
    }

    public List<ScoreDTO> query6Scores(String stuName, String cname) {

        StringBuilder sql = new StringBuilder(
            "SELECT b.stu_name as stuName,c.cname as courseName,a.score as score "
                + "FROM TB_SCORE a,TB_STUDENT b,TB_COURSE c "
                + "WHERE a.student_id=b.id and a.course_id=c.id ");

        List<Object> params = new ArrayList<Object>();

        StringBuilder where = new StringBuilder("");
        if (StringUtils.isNotBlank(stuName)) {
            where.append(" and b.stu_name=? ");
            params.add(stuName);
        }
        if (StringUtils.isNotBlank(cname)) {
            where.append(" and c.cname like ? ");
            params.add("%" + cname + "%");
        }
        sql = sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString(), "ScoreDTO");

        int index=1;
        for (Object param:params){
            query.setParameter(index,param);
            index++;
        }

        return query.getResultList();
    }

    public List<ScoreDTO> query7Scores(String stuName, String cname) {
        StringBuilder sql = new StringBuilder(
            "SELECT b.stu_name as stuName,c.cname as courseName,a.score as score "
                + "FROM TB_SCORE a,TB_STUDENT b,TB_COURSE c "
                + "WHERE a.student_id=b.id and a.course_id=c.id ");

        List<Object> params = new ArrayList<Object>();

        StringBuilder where = new StringBuilder("");
        if (StringUtils.isNotBlank(stuName)) {
            where.append(" and b.stu_name=? ");
            params.add(stuName);

        }
        if (StringUtils.isNotBlank(cname)) {
            where.append(" and c.cname like ? ");
            params.add("%" + cname + "%");
        }
        sql = sql.append(where);

        return jdbcTemplate.query(sql.toString(), params.toArray(new Object[params.size()]),
            new BeanPropertyRowMapper(ScoreDTO.class));
    }
}
