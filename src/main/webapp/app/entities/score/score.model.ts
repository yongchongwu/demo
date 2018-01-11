import { BaseEntity } from './../../shared';

export class Score implements BaseEntity {
    constructor(
        public id?: number,
        public score?: number,
        public student?: BaseEntity,
        public course?: BaseEntity,
    ) {
    }
}
