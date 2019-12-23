import {AfterViewInit, Component, OnDestroy, OnInit} from '@angular/core';
import {PostService} from "../../service/post.service";
import {Subject} from "rxjs";
import {PostModel} from "../../Model/postModel";
import {PostPageDto} from "../../Model/PostPageDto";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../service/user-service";
import {UserModel} from "../../Model/userModel";

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {
  itemsPerPage: number = 5;
  totalItems: any;
  page: any;
  previousPage: any;
  private posts: PostModel[];

  loadPage(page: number) {
    this.activatedRoute.paramMap.subscribe((params) => {
      if (page !== this.previousPage) {
        this.previousPage = page;
        if (params.get("tag") !== null) {
          this.postService.getPostsByTag(params.get("tag"),page-1 ).subscribe((postPages: PostPageDto) => {
            this.totalItems = postPages.totalPage * this.itemsPerPage;
            this.page = 1;
            this.posts = postPages.posts;
          })
        } else {
          this.userService.activeUser.subscribe((user:UserModel)=>{
            this.postService.getSubscriptionsPosts(page-1,user.id).subscribe(
              (posts: PostPageDto) => {
                this.posts = posts.posts;
                this.totalItems = posts.totalPage * this.itemsPerPage;
                this.page = posts.currentPage + 1;
              }
            );
          })

        }
      }
    })
  }

  constructor(private postService: PostService,
              private activatedRoute: ActivatedRoute,
              private userService:UserService) {
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe((params) => {
      if (params.get("tag") !== null) {
        this.postService.getPostsByTag(params.get("tag"), 0).subscribe((postPages: PostPageDto) => {
          this.totalItems = postPages.totalPage * this.itemsPerPage;
          this.page = 1;
          this.posts = postPages.posts;
        })
      } else {
        this.userService.activeUser.subscribe((user:UserModel)=>{
          this.postService.getSubscriptionsPosts(0,user.id).subscribe((posts: PostPageDto) => {
            this.totalItems = posts.totalPage * this.itemsPerPage;
            this.page = 1;
            this.posts = posts.posts;
          });
        })


      }
    })
  }

}
