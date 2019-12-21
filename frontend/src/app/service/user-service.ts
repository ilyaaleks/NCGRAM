import {Injectable, OnDestroy} from '@angular/core';
import {UserModel} from '../Model/userModel';
import {Observable, ReplaySubject, Subject} from 'rxjs';
import {HttpService} from './http.service';
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {post} from "selenium-webdriver/http";
import {JwtHelper} from "angular2-jwt";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class UserService{
  isAdmin: boolean;
  private usersStorage: UserModel[];
  private users: Subject<UserModel[]> = new ReplaySubject(1);
  private user: Subject<UserModel> = new ReplaySubject(1);
  private _activeUser: Subject<UserModel> = new ReplaySubject(1);
  private countOfSubscribers:Subject<number>=new ReplaySubject(1);
  private countOfSubscriptions: Subject<number>=new ReplaySubject(1);
  private countOfPosts: Subject<number>=new ReplaySubject(1);
  constructor(private httpClient: HttpClient) {
    let token=localStorage.getItem("token");
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(token);
    if(token!=null) {
      let user: UserModel = {
        id: decodedToken.id,
        name: decodedToken.name,
        surname: decodedToken.surname,
        aboutMe: decodedToken.aboutMe,
        login: decodedToken.sub,
        password: null,
        role: decodedToken.scopes,
        status: decodedToken.status,
        photoUrl: decodedToken.photoUrl,
        email: decodedToken.email,
      }
      this.activeUser.next(user);
    }
    else
    {
      this.activeUser.next(null);
    }
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


  public subscribe(id:number):void{
    this.activeUser.subscribe((user:UserModel)=>
    {
      if(user.id!==id)
      {
        this.httpClient.get('/api/user/subscribe?userId='+id+"&currentUserId="+user.id).subscribe((users: UserModel[]) => {

        })
      }
    })
  }
  public unsubscribe(id:number):void{
    this.activeUser.subscribe((user:UserModel)=>
    {
      if(user.id!==id)
      {
        this.httpClient.get('/api/user/unsubscribe?userId='+id).subscribe((users: UserModel[]) => {

        })
      }
    })
  }
  get activeUser(): Subject<UserModel> {
    return this._activeUser;
  }

  public getNumberSubscribers(id: string): Observable<number> {

    this.httpClient.get("/api/subscribers/count/" + id).subscribe((item: number) => {
        this.countOfSubscribers.next(item);

      }
    );
    return this.countOfSubscribers.asObservable();
  }

  public getNumberSubscribtions(id: string): Observable<number> {

    this.httpClient.get("/api/subscribtions/count/" + id).subscribe((item: number) => {
        this.countOfSubscriptions.next(item);
      }
    );
    return this.countOfSubscriptions.asObservable();
  }

  public getNumberPosts(id: string): Observable<number> {

    this.httpClient.get("/api/posts/" + id + "?count=true").subscribe((item: number) => {
        this.countOfPosts.next(item);
      }
    );
    return this.countOfPosts.asObservable();
  }

  public getUser(id: string): Observable<UserModel> {

    this.httpClient.get("/api/user/" + id).subscribe((user: UserModel) => {
      this.user.next(user);
    })
    return this.user.asObservable();
  }


  public saveUser(user: UserModel, file: File): Observable<boolean> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('name', user.name);
    formData.append('surname', user.surname);
    formData.append('aboutMe', user.aboutMe);
    formData.append('login', user.login);
    formData.append('password', user.password);
    formData.append('email', user.email);

    return this.httpClient.post('/api/users', formData).pipe(
      map(() => {
        return true;
      })
    );
  }

  public updateUser(user: UserModel): Observable<UserModel> {
    if (!this.user) {
      return this.httpClient.put<UserModel>("api/user", user);
    }
  }

  public deleteUser(user: UserModel): Observable<void> {
    if (!this.user) {
      return this.httpClient.delete<void>("api/user/" + user.id);
    }
  }
  public isAdminUser():boolean
  {
    return this.isAdmin;
  }

}
