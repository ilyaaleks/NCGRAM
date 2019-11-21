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
  public user: UserModel;
  public countOfSubscribers:number=15;
  public countOfSubscriptions:number=10;//спрсоить, как реализовывать просмотр подписчиков более лаконично
  public countOfPosts:number=3;
  constructor(
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private httpClient: HttpClient
  ) {
    this.subscriptions = this.activatedRoute.paramMap.subscribe(
      (params: ParamMap) => {
        this.userService.getUser(params.get("id")).subscribe((user: UserModel) => {
          this.user = user;
          console.log(this.user);
          }
        )


      });

  }

  ngOnInit() {

  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }


}
