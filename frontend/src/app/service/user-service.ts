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
  private users: Subject<UserModel[]> = new ReplaySubject(1);
  private user: Subject<UserModel> = new ReplaySubject(1);
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

  public getNumberSubscribers(id: string): number {
    let countOfSubscribers: number;
    this.httpClient.get("/api/subscribtions/" + id + "?count=true").subscribe((item: number) => {
        countOfSubscribers = item;
      }
    );
    return countOfSubscribers;
  }
  public getNumberSubscribtions(id: string): number {
    let countOfSubscriptions: number;
    this.httpClient.get("/api/subscribers/" + id + "?count=true").subscribe((item: number) => {
        countOfSubscriptions= item;
      }
    );
    return countOfSubscriptions;
  }
  public getNumberPosts(id: string): number {
    let countOfPosts: number;
    this.httpClient.get("/api/posts/" + id + "?count=true").subscribe((item: number) => {
        countOfPosts= item;
      }
    );
    return countOfPosts;
  }
  public getUser(id: string): Observable<UserModel> {

    this.httpClient.get("/api/user/" + id).subscribe((user: UserModel) => {
      this.user.next(user);
    })
    return this.user.asObservable();
  }

  public saveUser(user: UserModel): Observable<UserModel> {
    console.log("work saveUser method");
    return this.httpClient.post<UserModel>('/api/users', user);
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


}
