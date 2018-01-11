/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { DemoTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ScoreDetailComponent } from '../../../../../../main/webapp/app/entities/score/score-detail.component';
import { ScoreService } from '../../../../../../main/webapp/app/entities/score/score.service';
import { Score } from '../../../../../../main/webapp/app/entities/score/score.model';

describe('Component Tests', () => {

    describe('Score Management Detail Component', () => {
        let comp: ScoreDetailComponent;
        let fixture: ComponentFixture<ScoreDetailComponent>;
        let service: ScoreService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [DemoTestModule],
                declarations: [ScoreDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ScoreService,
                    JhiEventManager
                ]
            }).overrideTemplate(ScoreDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ScoreDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ScoreService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Score(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.score).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
