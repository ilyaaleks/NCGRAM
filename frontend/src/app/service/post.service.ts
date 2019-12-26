import {Injectable} from '@angular/core';
import {PostModel} from "../Model/postModel";
import {Observable, ReplaySubject, Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {PostPageDto} from "../Model/PostPageDto";
import {map} from "rxjs/operators";
import {UserService} from "./user-service";
import {UserModel} from "../Model/userModel";
import {ClaimModel} from "../Model/claim-model";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private _newPost:Subject<PostModel>=new ReplaySubject(1);
  private posts: Subject<PostModel[]> = new ReplaySubject(1);
  private postPages: Subject<PostPageDto> = new ReplaySubject(1);
  private post: Subject<PostModel> = new ReplaySubject(1);
  public deletedPostId:Subject<number>=new ReplaySubject(1);
  public updatedPost:Subject<PostModel>=new ReplaySubject(1);
  constructor(private httpClient: HttpClient) {
  }

  get newPost(): Subject<PostModel> {
    return this._newPost;
  }

  public getPageablePost() {
    return this.posts.asObservable();
  }

  public getPosts(page: number): Observable<PostPageDto> {

    this.httpClient.get('/api/posts?page=' + page + '&size=5&sort=id,DESC').subscribe((postPages: PostPageDto) => {
      this.posts.next(postPages.posts);
      this.postPages.next(postPages);
    });

    return this.postPages.asObservable();
  }

  public getUserPosts(page: number,userId:number): Observable<PostPageDto> {

    this.httpClient.get('/api/userPosts/'+userId+'?page=' + page + '&size=5&sort=id,DESC').subscribe((postPages: PostPageDto) => {//посмотреть и потом поменять
      this.posts.next(postPages.posts);
      this.postPages.next(postPages);
    });

    return this.postPages.asObservable();
  }

  public getSubscriptionsPosts(page,userId:number):Observable<PostPageDto>
  {
    this.httpClient.get('/api/subscribtionPosts/'+userId+'?page=' + page + '&size=5&sort=id,DESC').subscribe((postPages:PostPageDto)=>{
      this.posts.next(postPages.posts);
      this.postPages.next(postPages);
    })
    return this.postPages.asObservable();
  }

  public getPost(postId: number): Observable<PostModel> {
    this.httpClient.get("/api/post/" + postId).subscribe((post: PostModel) => {
      this.post.next(post);
    })
    return this.post.asObservable();
  }

  public getPostsByTag(tag:string,page: number)
  {
    this.httpClient.get('/api/postsByTag/'+tag+'?page=' + page + '&size=5&sort=id,DESC').subscribe((postPages: PostPageDto) => {
      this.posts.next(postPages.posts);
      this.postPages.next(postPages);
    });

    return this.postPages.asObservable()
  }

  public savePost(file: File, post: PostModel): Observable<PostModel> {
    const formData: FormData = new FormData();
    let hashTags:string;
    formData.append('file', file);
    formData.append('authorLogin', post.authorLogin);
    formData.append('hashTags', post.hashTags);
    formData.append('text', post.text);

    return this.httpClient.post<PostModel>('/api/posts', formData);


    // '/api/posts'
  }

  public updatePost(file: File,post: PostModel): void {
    if (post!=null) {
      const formData: FormData = new FormData();
      let hashTags:string;
      formData.append('file', file);
      formData.append('authorLogin', post.authorLogin);
      formData.append('hashTags', post.hashTags);
      formData.append('text', post.text);
      formData.append('postId',post.id.toString());
      this.httpClient.put<PostModel>("api/posts", formData).subscribe((post:PostModel)=>{
        this.updatedPost.next(post);
      });
    }
  }

  public deletePost(postId: number): Observable<void> {
    if (postId!==null && postId!==0) {
      this.deletedPostId.next(postId);
      return this.httpClient.delete<void>("api/post/" + postId);
    }
  }
  public sendClaim(claim:ClaimModel): Observable<ClaimModel>
  {
    return this.httpClient.post<ClaimModel>('/api/posts/claim', claim);
  }
  //public setLike()

}
