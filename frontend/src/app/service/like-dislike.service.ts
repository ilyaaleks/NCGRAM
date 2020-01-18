import {Injectable} from '@angular/core';
import {CommentModel} from "../Model/comment-model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LikeOrDislike} from "../Model/LikeOrDislike";

@Injectable({
  providedIn: 'root'
})
export class LikeDislikeService {

  constructor(private httpClient: HttpClient) {
  }

  public saveLike(like: LikeOrDislike): Observable<LikeOrDislike> {
    return this.httpClient.post<LikeOrDislike>('/api/like', like);
  }

  public getCountOfLike(postId:number,userId:number): Observable<LikeOrDislike> {
    return this.httpClient.get<LikeOrDislike>('/api/like/count/' + postId+"?userId="+userId);
  }

  // deleteBillingAccount(billingAccountId: string): Observable<void> {
  //   return this.http.delete<void>('/api/ba/' + billingAccountId);
  // }
}
