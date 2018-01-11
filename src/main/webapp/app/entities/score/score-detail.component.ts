import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Score } from './score.model';
import { ScoreService } from './score.service';

@Component({
    selector: 'jhi-score-detail',
    templateUrl: './score-detail.component.html'
})
export class ScoreDetailComponent implements OnInit, OnDestroy {

    score: Score;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private scoreService: ScoreService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInScores();
    }

    load(id) {
        this.scoreService.find(id).subscribe((score) => {
            this.score = score;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInScores() {
        this.eventSubscriber = this.eventManager.subscribe(
            'scoreListModification',
            (response) => this.load(this.score.id)
        );
    }
}
