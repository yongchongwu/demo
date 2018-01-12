package com.ifuture.demo.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ifuture.demo.domain.Score;
import com.ifuture.demo.service.ScoreService;
import com.ifuture.demo.service.dto.ScoreDTO;
import com.ifuture.demo.web.rest.util.HeaderUtil;
import com.ifuture.demo.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Score.
 */
@RestController
@RequestMapping("/api")
public class ScoreResource {

    private final Logger log = LoggerFactory.getLogger(ScoreResource.class);

    private static final String ENTITY_NAME = "score";

    private final ScoreService scoreService;

    public ScoreResource(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    /**
     * POST  /scores : Create a new score.
     *
     * @param score the score to create
     * @return the ResponseEntity with status 201 (Created) and with body the new score, or with
     * status 400 (Bad Request) if the score has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/scores")
    @Timed
    public ResponseEntity<Score> createScore(@Valid @RequestBody Score score)
        throws URISyntaxException {
        log.debug("REST request to save Score : {}", score);
        if (score.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil
                .createFailureAlert(ENTITY_NAME, "idexists",
                    "A new score cannot already have an ID")).body(null);
        }
        Score result = scoreService.save(score);
        return ResponseEntity.created(new URI("/api/scores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /scores : Updates an existing score.
     *
     * @param score the score to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated score, or with status
     * 400 (Bad Request) if the score is not valid, or with status 500 (Internal Server Error) if the
     * score couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/scores")
    @Timed
    public ResponseEntity<Score> updateScore(@Valid @RequestBody Score score)
        throws URISyntaxException {
        log.debug("REST request to update Score : {}", score);
        if (score.getId() == null) {
            return createScore(score);
        }
        Score result = scoreService.save(score);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, score.getId().toString()))
            .body(result);
    }

    /**
     * GET  /scores : get all the scores.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of scores in body
     */
    @GetMapping("/scores")
    @Timed
    public ResponseEntity<List<Score>> getAllScores(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Scores");
        Page<Score> page = scoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/scores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /scores/:id : get the "id" score.
     *
     * @param id the id of the score to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the score, or with status 404
     * (Not Found)
     */
    @GetMapping("/scores/{id}")
    @Timed
    public ResponseEntity<Score> getScore(@PathVariable Long id) {
        log.debug("REST request to get Score : {}", id);
        Score score = scoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(score));
    }

    /**
     * DELETE  /scores/:id : delete the "id" score.
     *
     * @param id the id of the score to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/scores/{id}")
    @Timed
    public ResponseEntity<Void> deleteScore(@PathVariable Long id) {
        log.debug("REST request to delete Score : {}", id);
        scoreService.delete(id);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/scores/search")
    @Timed
    public ResponseEntity<List<Score>> searchScores(@RequestParam(value = "stuName") String stuName,
        @RequestParam(value = "cname") String cname) {
        List<Score> list = scoreService.searchScores(stuName, cname);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/scores/query")
    @Timed
    public ResponseEntity<List<Score>> queryScores(
        @RequestParam(value = "stuName", required = false) String stuName,
        @RequestParam(value = "cname", required = false) String cname) {
        List<Score> list = scoreService.queryScores(stuName, cname);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/scores/query2")
    @Timed
    public ResponseEntity<List<Score>> query2Scores(
        @RequestParam(value = "stuName", required = false) String stuName,
        @RequestParam(value = "cname", required = false) String cname) {
        List<Score> list = scoreService.query2Scores(stuName, cname);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/scores/query3")
    @Timed
    public ResponseEntity<List<Score>> query3Scores(
        @RequestParam(value = "stuName", required = false) String stuName,
        @RequestParam(value = "cname", required = false) String cname) {
        List<Score> list = scoreService.query3Scores(stuName, cname);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/scores/query4")
    @Timed
    public ResponseEntity<List<ScoreDTO>> query4Scores(
        @RequestParam(value = "stuName", required = false) String stuName,
        @RequestParam(value = "cname", required = false) String cname) {
        List<ScoreDTO> list = scoreService.query4Scores(stuName, cname);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/scores/query5")
    @Timed
    public ResponseEntity<List<ScoreDTO>> query5Scores(
        @RequestParam(value = "stuName", required = false) String stuName,
        @RequestParam(value = "cname", required = false) String cname) {
        List<ScoreDTO> list = scoreService.query5Scores(stuName, cname);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/scores/query6")
    @Timed
    public ResponseEntity<List<ScoreDTO>> query6Scores(
        @RequestParam(value = "stuName", required = false) String stuName,
        @RequestParam(value = "cname", required = false) String cname) {
        List<ScoreDTO> list = scoreService.query6Scores(stuName, cname);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/scores/query7")
    @Timed
    public ResponseEntity<List<ScoreDTO>> query7Scores(
        @RequestParam(value = "stuName", required = false) String stuName,
        @RequestParam(value = "cname", required = false) String cname) {
        List<ScoreDTO> list = scoreService.query7Scores(stuName, cname);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/scores/query8")
    @Timed
    public ResponseEntity<Page> query8Scores(
        @RequestParam(value = "stuName", required = false) String stuName,
        @RequestParam(value = "cname", required = false) String cname,
        @ApiParam Pageable pageable) {
        Page page = scoreService.query8Scores(stuName, cname, pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/scores/query9")
    @Timed
    public ResponseEntity<List<ScoreDTO>> query9Scores(
        @RequestParam(value = "stuName", required = false) String stuName,
        @RequestParam(value = "cname", required = false) String cname) {
        List<ScoreDTO> list = scoreService.query9Scores(stuName, cname);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/scores/query10")
    @Timed
    public ResponseEntity<Page> query10Scores(
        @RequestParam(value = "stuName", required = false) String stuName,
        @RequestParam(value = "cname", required = false) String cname,
        @ApiParam Pageable pageable) {
        Page page = scoreService.query10Scores(stuName, cname, pageable);
        return ResponseEntity.ok().body(page);
    }

}
