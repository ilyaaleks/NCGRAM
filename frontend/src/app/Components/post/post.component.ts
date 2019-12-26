import {Component, Input, OnInit} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {DialogwindowComponent} from '../dialogwindow/dialogwindow.component';
import {UserService} from "../../service/user-service";
import {UserModel} from "../../Model/userModel";
// import {CommentService} from "../../service/comment.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CommentModel} from "../../Model/comment-model";
import {CommentService} from "../../service/comment.service";
import {CommentPageDto} from "../../Model/comment-page-dto";
import {LikeDislikeService} from "../../service/like-dislike.service";
import {LikeOrDislike} from "../../Model/LikeOrDislike";
import {TypeOfVote} from "../../Model/type-of-vote.enum";
import {PostService} from "../../service/post.service";
import {ReplaySubject, Subject} from "rxjs";
import {NotificationsComponent} from "../notifications/notifications.component";
import {PostModel} from "../../Model/postModel";
import {ClaimModel} from "../../Model/claim-model";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  @Input()
  author;
  @Input()
  description;
  @Input()
  date;
  @Input() private _hashTags;
  @Input()
  src
  @Input()
  id;
  @Input()
  authorPhotoPath;
  @Input() private _authorId;
  private activeUser: boolean;
  public commentForm: FormGroup;
  itemsPerPage: number = 5;
  totalItems: any;
  page: any;
  previousPage: any;
  private comments: CommentModel[];
  private countOfLikes: number;
  private countOfDislike: number;
  private typeOfVoteUser: TypeOfVote;
  private Like = TypeOfVote.Like;
  private Dislike = TypeOfVote.Dislike;
  private idofLike: number;


  constructor(public dialog: MatDialog,
              private userService: UserService,
              private commentService: CommentService,
              private likeService: LikeDislikeService,
              private postService: PostService
  ) {

  }

  getUrl() {
    if (this.authorPhotoPath != null) {
      return `url(\'http://localhost:8083/api/photo/${this.authorPhotoPath}\')`;
    }
  }

  getPostUrl() {
    if (this.src != null) {
      return "http://localhost:8083/api/photo/" + this.src;
    }
  }

  get authorId() {
    return "user/" + this._authorId;
  }



  get hashTags() {
    return this._hashTags.id;
  }

  ngOnInit() {
    this.commentForm = new FormGroup({
      comment: new FormControl('', [Validators.required])
    })
    ;
    this.userService.activeUser.subscribe((user: UserModel) => {
      if (this.authorId != null) {
        if (user.id === this._authorId) {
          this.activeUser = true;
        } else {
          this.activeUser = false;
        }
      }

    })
    this.commentService.getCommentsofPost(0, this.id).subscribe((commentPage: CommentPageDto) => {
      this.totalItems = commentPage.countOfComments;
      this.page = 1;
      this.comments = commentPage.comments;
    });
    this.userService.activeUser.subscribe((user: UserModel) => {
      this.likeService.getCountOfLike(this.id, user.id).subscribe((likeOrDislike: LikeOrDislike) => {
        this.countOfLikes = likeOrDislike.countOfLikes;
        this.countOfDislike = likeOrDislike.countOfDislikes;
        this.idofLike = likeOrDislike.id;
        if (likeOrDislike.typeOfVote === null) {
          likeOrDislike.typeOfVote = TypeOfVote.Nothing;
        } else {
          this.typeOfVoteUser = likeOrDislike.typeOfVote;
        }
      })
    })
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.commentService.getCommentsofPost(page - 1, this.id).subscribe((commentPage: CommentPageDto) => {
        this.totalItems = commentPage.countOfComments;
        this.page = commentPage.currentPage + 1;
        this.comments = commentPage.comments;
      });
    }
  }

  addComment() {
    this.userService.activeUser.subscribe((user: UserModel) => {
      let comment: CommentModel = {
        id: null,
        text: this.commentForm.controls['comment'].value,
        date: null,
        postId: this.id,
        userId: user.id,
      }
      this.commentService.saveComment(comment).subscribe((comment: CommentModel) => {
        this.commentForm.controls['comment'].reset("")
        if (this.comments.length === 5 && this.page === 1 && comment.postId === this.id) {
          this.comments.unshift(comment);
          this.comments.splice(4, 1);
          this.totalItems++;
        } else if (this.page !== 1) {
          this.totalItems++;
        } else {
          this.totalItems++;
          this.comments.unshift(comment);
        }

      });
    })

  }

  public setLike() {
    this.userService.activeUser.subscribe((user: UserModel) => {
      let like: LikeOrDislike = {
        id: this.idofLike,
        postId: this.id,
        authorId: user.id,
        typeOfVote: TypeOfVote.Like,
        date: null
      }
      this.likeService.saveLike(like).subscribe((likeOrDislike: LikeOrDislike) => {
        this.countOfLikes = likeOrDislike.countOfLikes;
        this.countOfDislike = likeOrDislike.countOfDislikes;
        this.typeOfVoteUser = likeOrDislike.typeOfVote;
        this.idofLike = likeOrDislike.id;
      });
    })

  }

  public setDislike() {
    this.userService.activeUser.subscribe((user: UserModel) => {
      let like: LikeOrDislike = {
        id: this.idofLike,
        postId: this.id,
        authorId: user.id,
        typeOfVote: TypeOfVote.Dislike,
        date: null
      }
      this.likeService.saveLike(like).subscribe((likeOrDislike: LikeOrDislike) => {
        this.countOfLikes = likeOrDislike.countOfLikes;
        this.countOfDislike = likeOrDislike.countOfDislikes;
        this.typeOfVoteUser = likeOrDislike.typeOfVote;
        this.idofLike = likeOrDislike.id;
      });
    })
  }

  public deletePost() {
    this.postService.deletePost(this.id).subscribe();

  }
  public updatePost(){
    const dialogConfig = new MatDialogConfig();
    let tagStr:string;
    this._hashTags.forEach(item=>tagStr+=" #"+item.text)
    if(tagStr!=null) {
      tagStr = tagStr.replace("undefined ", "");
    }
    let post:PostModel={
      id:this.id,
      authorLogin:this.author,
      photoPath:null,
      text:this.description,
      date:null,
      hashTags:tagStr
    }
    dialogConfig.width = '50%';
    dialogConfig.data={
      post:post,
    }
    this.dialog.open(NotificationsComponent, dialogConfig);
    // formData.append('file', file);
    // formData.append('authorLogin', post.authorLogin);
    // formData.append('hashTags', post.hashTags);
    // formData.append('text', post.text);
    // formData.append('postId',post.id.toString());
  }
  public sendReport()
  {
    this.userService.activeUser.subscribe((user:UserModel)=>{
      let claim:ClaimModel={
        id:null,
        postId:this.id,
        date:new Date(),
        reason:null,
        status:"Active",
        authorId:user.id
      }
      const dialogConfig = new MatDialogConfig();
      dialogConfig.data={
        post:claim,
      }
      dialogConfig.width = '30%';
      this.dialog.open(DialogwindowComponent, dialogConfig);
    })

  }
}
