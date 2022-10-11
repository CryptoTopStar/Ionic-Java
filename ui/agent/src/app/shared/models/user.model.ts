import { Role } from './role.model';

export class User {
    id: number;
    username: string;
    password: string;
    email: string;
    mobile: string;
    active: boolean;
    lastName: string;
    roles : Role[];
}