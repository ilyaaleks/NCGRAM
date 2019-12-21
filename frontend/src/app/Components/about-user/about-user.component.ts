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
  public pageOfCurrentUser:boolean;
  public isSubscribed:boolean;
  public countOfSubscribers:number;
  public countOfSubscriptions:number;
  public countOfPosts:number;
  constructor(
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private httpClient: HttpClient
  ) {
    this.subscriptions = this.activatedRoute.paramMap.subscribe(
      (params: ParamMap) => {
        this.userService.getUser(params.get("id")).subscribe((user: UserModel) => {
          this.user = user;
          userService.activeUser.subscribe((activeUser:UserModel)=>
          {
            if(user.id===activeUser.id)
            {
              this.pageOfCurrentUser=false;
            }
            else
            {
              this.pageOfCurrentUser=true;
            }
          })
          }
        )
        this.userService.getNumberSubscribers(params.get("id")).subscribe((item:number)=>{
          this.countOfSubscribers=item;
          if(this.countOfSubscribers==null)
          {
            this.countOfSubscribers=0;
          }
        });
        this.userService.getNumberSubscribtions(params.get("id")).subscribe((item:number)=>{
          this.countOfSubscriptions=item;
          if(this.countOfSubscriptions==null)
          {
            this.countOfSubscriptions=0;
          }
        });
        this.userService.getNumberPosts(params.get("id")).subscribe((item:number)=>{
          this.countOfPosts=item
          if(this.countOfPosts==null)
          {
            this.countOfPosts=0;
          }
        });

      });



  }
  subscribe()
  {
    this.activatedRoute.paramMap.subscribe((params)=>{
      this.userService.subscribe(Number.parseInt(params.get("id")))
      this.isSubscribed=!this.isSubscribed;
    })

  }
  unsubscribe()
  {
    this.activatedRoute.paramMap.subscribe((params)=>{
      this.userService.unsubscribe(Number.parseInt(params.get("id")))
      this.isSubscribed=!this.isSubscribed;
    })
  }
  ngOnInit() {

  }
  getUrl()
  {
    if(this.user!=null) {
      return "http://localhost:8083/api/photo/" + this.user.photoUrl;
    }
  }
  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }


}
