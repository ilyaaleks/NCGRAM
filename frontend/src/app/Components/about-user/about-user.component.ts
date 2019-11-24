import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../../service/user-service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {UserModel} from "../../Model/userModel";
import {Subscription} from "rxjs";
import {map} from "rxjs/operators";
import {__param} from "tslib";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-about-user',
  templateUrl: './about-user.component.html',
  styleUrls: ['./about-user.component.css']
})
export class AboutUserComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription;
  public activeUser: UserModel;
  public countOfSubscribers:number;
  public countOfSubscriptions:number;//спрсоить, как реализовывать просмотр подписчиков более лаконично
  public countOfPosts:number;
  constructor(
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private httpClient: HttpClient
  ) {
    this.subscriptions = this.activatedRoute.paramMap.subscribe(
      (params: ParamMap) => {
        this.userService.getUser(params.get("id")).subscribe((user: UserModel) => {
          this.activeUser = user;
          }
        )
        this.countOfSubscribers=this.userService.getNumberSubscribers(params.get("id"));
        this.countOfSubscriptions=this.userService.getNumberSubscribtions(params.get("id"));
        this.countOfPosts=this.userService.getNumberPosts(params.get("id"));
      });
    if(this.countOfSubscriptions==null)
    {
      this.countOfSubscriptions=0;
    }
    if(this.countOfSubscribers==null)
    {
      this.countOfSubscribers=0;
    }
    if(this.countOfPosts==null)
    {
      this.countOfPosts=0;
    }


  }

  ngOnInit() {

  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }


}
