import { Injectable } from '@angular/core';
import {PostModel} from "../Model/postModel";
import {Observable, ReplaySubject, Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private postsStorage:PostModel[];
  private posts: Subject<PostModel[]>=new ReplaySubject(1);
  private post:Subject<PostModel>=new ReplaySubject(1);
  constructor(private httpClient: HttpClient) { }
  public getPosts(): Observable<PostModel[]> {
    if (!this.posts) {
      this.httpClient.get('/api/posts').subscribe((posts: PostModel[]) => {
        this.posts.next(posts);
        this.postsStorage=posts;
      });
    }
    return this.posts.asObservable();
  }

  public getPost(postId: number): Observable<PostModel>{
    this.httpClient.get("/api/post/"+postId).subscribe((post: PostModel)=>
    {
      this.post.next(post);
    })
    return this.post.asObservable();
  }
  public savePost(post: PostModel): Observable<PostModel> {
    if (!this.post) {
      return this.httpClient.post<PostModel>('/api/posts',post);
    }
  }
  public updatePost(post:PostModel):Observable<PostModel>
  {
    if(!this.post)
    {
      return this.httpClient.put<PostModel>("api/post",post);
    }
  }
  public deletePost(post:PostModel):Observable<void>
  {
    if(!this.post)
    {
      return this.httpClient.delete<void>("api/post/"+post.id);
    }
  }
}
