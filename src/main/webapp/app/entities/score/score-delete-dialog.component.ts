import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Score } from './score.model';
import { ScorePopupService } from './score-popup.service';
import { ScoreService } from './score.service';

@Component({
    selector: 'jhi-score-delete-dialog',
    templateUrl: './score-delete-dialog.component.html'
})
export class ScoreDeleteDialogComponent {

    score: Score;

    constructor(
        private scoreService: ScoreService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.scoreService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'scoreListModification',
                content: 'Deleted an score'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-score-delete-popup',
    template: ''
})
export class ScoreDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private scorePopupService: ScorePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.scorePopupService
                .open(ScoreDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
