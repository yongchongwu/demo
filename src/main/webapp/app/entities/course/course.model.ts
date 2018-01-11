import { BaseEntity } from './../../shared';

export class Course implements BaseEntity {
    constructor(
        public id?: number,
        public cname?: string,
        public ctime?: number,
        public credit?: number,
    ) {
    }
}
