import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {UserModel} from "../../Model/userModel";
import {UserService} from "../../service/user-service";
import {Router} from "@angular/router";
import {PostModel} from "../../Model/postModel";
import {PostService} from "../../service/post.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Observable} from "rxjs";

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {
  public postForm: FormGroup;
  public status:any;
  constructor(private userService:UserService,
              private postService:PostService,
              private router: Router,
              public dialogRef: MatDialogRef<NotificationsComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any
              ) { }

  ngOnInit() {
    this.postForm = new FormGroup({
      text: new FormControl(''),
      tags: new FormControl('', [Validators.required]),
      file: new FormControl(null, [Validators.required])
    })
    ;
    if(this.data!==null)
    {
      let post:PostModel=this.data.post;
      this.postForm.controls['text'].setValue(post.text);
      this.postForm.controls['tags'].setValue(post.hashTags);
    }
  }

  fileToUpload: File = null;
  public onFileChange(files: FileList)
  {
    this.fileToUpload = files.item(0);
  }
  public addPost():void {
    let post: PostModel = {
      id: null,
      authorLogin: null,
      photoPath: null,
      text: this.postForm.controls['text'].value,
      date: new Date().toString(),
      hashTags: this.postForm.controls['tags'].value
    }
    this.userService.activeUser.subscribe((user:UserModel)=>
    {
      post.authorLogin=user.login;
      this.postService.savePost(this.fileToUpload,post).subscribe((post:PostModel)=>{
        this.postService.newPost.next(post);
      },(err)=>{

      })
    })

  }
  public update(){
    let post: PostModel = {
      id: this.data.post.id,
      authorLogin: this.data.post.authorLogin,
      photoPath: null,
      text: this.postForm.controls['text'].value,
      date: new Date().toString(),
      hashTags: this.postForm.controls['tags'].value
    }
    this.postService.updatePost(this.fileToUpload,post);
  }
}
