import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { DemoStudentModule } from './student/student.module';
import { DemoCourseModule } from './course/course.module';
import { DemoScoreModule } from './score/score.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        DemoStudentModule,
        DemoCourseModule,
        DemoScoreModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DemoEntityModule {}
