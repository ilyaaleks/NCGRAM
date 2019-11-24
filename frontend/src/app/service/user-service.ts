import {Injectable} from '@angular/core';
import {UserModel} from '../Model/userModel';
import {Observable, ReplaySubject, Subject} from 'rxjs';
import {HttpService} from './http.service';
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {post} from "selenium-webdriver/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private usersStorage: UserModel[];
  private users: Subject<UserModel[]>=new ReplaySubject(1);
  private user: Subject<UserModel>=new ReplaySubject(1);
  constructor(private httpClient: HttpClient) {
  }
  public getUsers(): Observable<UserModel[]> {
    if (!this.users) {
      this.httpClient.get('/api/users').subscribe((users: UserModel[]) => {
        this.usersStorage = users;
        this.users.next(users);
      });
    }
    return this.users.asObservable();
  }

  public getUser(id: string): Observable<UserModel>{
    // this.getUsers().pipe(map
    // (user => user.find(userfn => userfn.login === login))).subscribe(user=>this.user=user);
    this.httpClient.get("/api/user/"+id).subscribe((user: UserModel)=>
    {
      this.user.next(user);
    })
    return this.user.asObservable();
  }
  public saveUser(user: UserModel): Observable<UserModel> {
      return this.httpClient.post<UserModel>('/api/users',user);
  }
  public updateUser(user:UserModel):Observable<UserModel>
  {
    if(!this.user)
    {
      return this.httpClient.put<UserModel>("api/user",user);
    }
  }
  public deleteUser(user:UserModel):Observable<void>
  {
    if(!this.user)
    {
      return this.httpClient.delete<void>("api/user/"+user.id);
    }
  }


}
