import { Injectable } from '@angular/core';
import {Observable, ReplaySubject, Subject} from "rxjs";
import {PostModel} from "../Model/postModel";
import {PostPageDto} from "../Model/PostPageDto";
import {HttpClient} from "@angular/common/http";
import {CommentModel} from "../Model/comment-model";
import {CommentPageDto} from "../Model/comment-page-dto";
import {UserService} from "./user-service";

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private comments: Subject<CommentModel[]> = new ReplaySubject(1);
  private commentPage: Subject<CommentPageDto> = new ReplaySubject(1);
  private comment: Subject<CommentModel> = new ReplaySubject(1);

  constructor(private httpClient: HttpClient,
              private userService:UserService) {
  }



  public getCommentsofPost(page: number,postId:number): Observable<CommentPageDto> {

    this.httpClient.get('/api/comment/'+postId+'?page=' + page + '&size=5&sort=id,DESC').subscribe((commentPages: CommentPageDto) => {//посмотреть и потом поменять
      this.comments.next(commentPages.comments);
      this.commentPage.next(commentPages);
    });

    return this.commentPage.asObservable();
  }

  public saveComment(comment: CommentModel): Observable<CommentModel> {
    return this.httpClient.post<CommentModel>('/api/comment', comment);
  }


  public deleteComment(comment: CommentModel): Observable<void> {
    return this.httpClient.delete<void>("api/comment/" + comment.id);
  }
}
