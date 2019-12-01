import {AfterViewInit, Component, OnDestroy, OnInit} from '@angular/core';
import {PostService} from "../../service/post.service";
import {Subject} from "rxjs";
import {PostModel} from "../../Model/postModel";
import {PostPageDto} from "../../Model/PostPageDto";

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit{
  itemsPerPage: number=5;
  totalItems: any;
  page: any;
  previousPage: any;
  private posts: PostModel[];
  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.postService.getPosts(page-1).subscribe(
        (posts:PostPageDto)=>{
          this.posts=posts.posts;
          this.totalItems=posts.totalPage*this.itemsPerPage;
          this.page=posts.currentPage+1;
        }
      );

    }
  }
  constructor(private postService:PostService) { }
  ngOnInit() {
    this.postService.getPosts(0).subscribe((posts:PostPageDto)=>
    {
      this.totalItems=posts.totalPage*this.itemsPerPage;
      this.page=1;
      this.posts=posts.posts;
    });




  }



}
