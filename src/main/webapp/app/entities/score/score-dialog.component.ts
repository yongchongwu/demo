import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Score } from './score.model';
import { ScorePopupService } from './score-popup.service';
import { ScoreService } from './score.service';
import { Student, StudentService } from '../student';
import { Course, CourseService } from '../course';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-score-dialog',
    templateUrl: './score-dialog.component.html'
})
export class ScoreDialogComponent implements OnInit {

    score: Score;
    isSaving: boolean;

    students: Student[];

    courses: Course[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private scoreService: ScoreService,
        private StudentService: StudentService,
        private CourseService: CourseService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.StudentService.query()
            .subscribe((res: ResponseWrapper) => { this.students = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.CourseService.query()
            .subscribe((res: ResponseWrapper) => { this.courses = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.score.id !== undefined) {
            this.subscribeToSaveResponse(
                this.scoreService.update(this.score));
        } else {
            this.subscribeToSaveResponse(
                this.scoreService.create(this.score));
        }
    }

    private subscribeToSaveResponse(result: Observable<Score>) {
        result.subscribe((res: Score) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Score) {
        this.eventManager.broadcast({ name: 'scoreListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackStudentById(index: number, item: Student) {
        return item.id;
    }

    trackCourseById(index: number, item: Course) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-score-popup',
    template: ''
})
export class ScorePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private scorePopupService: ScorePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.scorePopupService
                    .open(ScoreDialogComponent as Component, params['id']);
            } else {
                this.scorePopupService
                    .open(ScoreDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
