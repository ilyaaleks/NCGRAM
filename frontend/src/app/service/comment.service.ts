import { Injectable } from '@angular/core';
import {Observable, ReplaySubject, Subject} from "rxjs";
import {PostModel} from "../Model/postModel";
import {PostPageDto} from "../Model/PostPageDto";
import {HttpClient} from "@angular/common/http";
import {CommentModel} from "../Model/comment-model";
import {CommentPageDto} from "../Model/comment-page-dto";

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  constructor(private httpClient: HttpClient) {
  }



  public getCommentsofPost(page: number,postId:number): Observable<CommentPageDto> {

    return this.httpClient.get<CommentPageDto>('/api/comment/'+postId+'?page=' + page + '&size=5&sort=id,DESC');
  }

  public saveComment(comment: CommentModel): Observable<CommentModel> {
    return this.httpClient.post<CommentModel>('/api/comment', comment);
  }


  public deleteComment(comment: CommentModel): Observable<void> {
    return this.httpClient.delete<void>("api/comment/" + comment.id);
  }
}
