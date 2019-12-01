import { Injectable } from '@angular/core';
import {PostModel} from "../Model/postModel";
import {Observable, ReplaySubject, Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {PostPageDto} from "../Model/PostPageDto";
@Injectable({
  providedIn: 'root'
})
export class PostService {

  private posts: Subject<PostModel[]> = new ReplaySubject(1);
  private postPages: Subject<PostPageDto> = new ReplaySubject(1);
  private post: Subject<PostModel> = new ReplaySubject(1);

  constructor(private httpClient: HttpClient) {
  }
  public getPageablePost()
  {
    return this.posts.asObservable();
  }

  public getPosts(page: number): Observable<PostPageDto> {

    this.httpClient.get('/api/posts?page='+page+'&size=5&sort=id,DESC').subscribe((postPages: PostPageDto) => {//посмотреть и потом поменять
      this.posts.next(postPages.posts);
      this.postPages.next(postPages);
    });

    return this.postPages.asObservable();
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
