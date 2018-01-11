import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ScoreComponent } from './score.component';
import { ScoreDetailComponent } from './score-detail.component';
import { ScorePopupComponent } from './score-dialog.component';
import { ScoreDeletePopupComponent } from './score-delete-dialog.component';

@Injectable()
export class ScoreResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const scoreRoute: Routes = [
    {
        path: 'score',
        component: ScoreComponent,
        resolve: {
            'pagingParams': ScoreResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demoApp.score.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'score/:id',
        component: ScoreDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demoApp.score.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const scorePopupRoute: Routes = [
    {
        path: 'score-new',
        component: ScorePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demoApp.score.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'score/:id/edit',
        component: ScorePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demoApp.score.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'score/:id/delete',
        component: ScoreDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demoApp.score.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
