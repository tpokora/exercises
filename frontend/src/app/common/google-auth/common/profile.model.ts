export class Profile {
    id: number;
    name: string;
    email: string;
    token: string;

    constructor() {
        this.id = undefined
        this.name = '';
        this.email = '';
        this.token = '';
    }
}
