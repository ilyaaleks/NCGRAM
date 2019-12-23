import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../../service/user-service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {UserModel} from "../../Model/userModel";
import {ReplaySubject, Subject, Subscription} from "rxjs";
import {map} from "rxjs/operators";
import {__param} from "tslib";
import {HttpClient} from "@angular/common/http";
import {PostPageDto} from "../../Model/PostPageDto";
import {PostService} from "../../service/post.service";
import {PostModel} from "../../Model/postModel";

@Component({
  selector: 'app-about-user',
  templateUrl: './about-user.component.html',
  styleUrls: ['./about-user.component.css']
})
export class AboutUserComponent implements OnInit, OnDestroy {
  get user(): UserModel {
    return this._user;
  }
  private subscriptions: Subscription;
  private _user: UserModel;
  public pageOfCurrentUser: boolean;
  public subscribed: boolean;
  public countOfSubscribers: number;
  public countOfSubscriptions: number;
  public countOfPosts: number;
  itemsPerPage: number = 5;
  totalItems: any;
  page: any;
  previousPage: any;
  private posts: PostModel[];

  constructor(
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private httpClient: HttpClient,
    private postService: PostService
  ) {


  }
  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.postService.getUserPosts(page-1,this._user.id).subscribe(
        (posts:PostPageDto)=>{
          this.posts=posts.posts;
          this.page=posts.currentPage+1;
        }
      );

    }
  }

  subscribe() {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.userService.subscribe(Number.parseInt(params.get("id")))
      this._user.subscribed = !this._user.subscribed;
    })

  }

  unsubscribe() {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.userService.unsubscribe(Number.parseInt(params.get("id")))
      this._user.subscribed = !this._user.subscribed;
    })
  }

  ngOnInit() {
    this.subscriptions = this.activatedRoute.paramMap.subscribe(
      (params: ParamMap) => {
        this.userService.getUser(params.get("id")).subscribe((user: UserModel) => {
            this._user = user;
            this.postService.getUserPosts(0,this._user.id).subscribe((posts: PostPageDto) => {
              this.totalItems = posts.totalPage * this.itemsPerPage;
              this.page = 1;
              this.posts = posts.posts;
              this.subscribed=user.subscribed;
            });
            this.userService.activeUser.subscribe((activeUser: UserModel) => {
              if (user.id === activeUser.id) {
                this.pageOfCurrentUser = false;
              } else {
                this.pageOfCurrentUser = true;
              }
            })
          }
        )
        this.userService.getNumberSubscribers(params.get("id")).subscribe((item: number) => {
          this.countOfSubscribers = item;
          if (this.countOfSubscribers == null) {
            this.countOfSubscribers = 0;
          }
        });
        this.userService.getNumberSubscribtions(params.get("id")).subscribe((item: number) => {
          this.countOfSubscriptions = item;
          if (this.countOfSubscriptions == null) {
            this.countOfSubscriptions = 0;
          }
        });
        this.userService.getNumberPosts(params.get("id")).subscribe((item: number) => {
          this.countOfPosts = item
          if (this.countOfPosts == null) {
            this.countOfPosts = 0;
          }
        });

      });

    this.postService.newPost.subscribe((post:PostModel)=>
    {
      if(post!=null)
      {
        if(this.posts.length===5 && this.page===1 && !this.pageOfCurrentUser) {
          this.posts.splice(4, 1);
          this.posts.unshift(post);
          this.postService.newPost.next(null);
          this.countOfPosts++;

        }
        else if( this.page===1 && !this.pageOfCurrentUser)
        {
          this.posts.unshift(post);
          this.postService.newPost.next(null);
          this.countOfPosts++;
        }
        else
        {
          this.postService.newPost.next(null);
        }
      }
    })

  }

  getUrl() {
    if (this._user != null) {
      return "http://localhost:8083/api/photo/" + this._user.photoUrl;
    }
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }


}
