import {Injectable} from '@angular/core';
import {UserModel} from '../Model/userModel';
import {Observable, ReplaySubject, Subject} from 'rxjs';
import {HttpService} from './http.service';
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private usersStorage: UserModel[];
  private users: Subject<UserModel[]>=new ReplaySubject(1);
  private user: Subject<UserModel>=new ReplaySubject(1);
  constructor(private httpService: HttpService) {
  }

  public getUsers(): Observable<UserModel[]> {
    if (!this.users) {
      this.httpService.get('/api/users').subscribe((users: UserModel[]) => {
        this.usersStorage = users;
        this.users.next(users);
      });
    }
    return this.users.asObservable();
  }

  public getUser(login: string): Observable<UserModel>{
    // this.getUsers().pipe(map
    // (user => user.find(userfn => userfn.login === login))).subscribe(user=>this.user=user);
    this.httpService.get("/api/user/"+login).subscribe((user: UserModel)=>
    {
      this.user.next(user);
    })
    return this.user.asObservable();
  }

}
