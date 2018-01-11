import { BaseEntity, User } from './../../shared';

export class Student implements BaseEntity {
    constructor(
        public id?: number,
        public stuName?: string,
        public sex?: string,
        public phone?: string,
        public age?: number,
        public address?: string,
        public remark?: string,
        public birthDate?: any,
        public user?: User,
    ) {
    }
}
