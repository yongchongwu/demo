import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DemoSharedModule } from '../../shared';
import {
    ScoreService,
    ScorePopupService,
    ScoreComponent,
    ScoreDetailComponent,
    ScoreDialogComponent,
    ScorePopupComponent,
    ScoreDeletePopupComponent,
    ScoreDeleteDialogComponent,
    scoreRoute,
    scorePopupRoute,
    ScoreResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...scoreRoute,
    ...scorePopupRoute,
];

@NgModule({
    imports: [
        DemoSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ScoreComponent,
        ScoreDetailComponent,
        ScoreDialogComponent,
        ScoreDeleteDialogComponent,
        ScorePopupComponent,
        ScoreDeletePopupComponent,
    ],
    entryComponents: [
        ScoreComponent,
        ScoreDialogComponent,
        ScorePopupComponent,
        ScoreDeleteDialogComponent,
        ScoreDeletePopupComponent,
    ],
    providers: [
        ScoreService,
        ScorePopupService,
        ScoreResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DemoScoreModule {}
