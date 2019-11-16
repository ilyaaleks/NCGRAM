import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../service/user-service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {UserModel} from "../Model/userModel";
import {Subscription} from "rxjs";
import {map} from "rxjs/operators";
import {__param} from "tslib";

@Component({
  selector: 'app-about-user',
  templateUrl: './about-user.component.html',
  styleUrls: ['./about-user.component.css']
})
export class AboutUserComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription;
  public user: UserModel;
  public subscriberUsers;
  public subscriptionUsers;//спрсоить, как реализовывать просмотр подписчиков
  constructor(
    private activatedRoute: ActivatedRoute,
    private userService: UserService
  ) {
    this.subscriptions = this.activatedRoute.paramMap.subscribe(
      (params: ParamMap) => {
        this.userService.getUser(params.get("login")).subscribe((user: UserModel) => {
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
