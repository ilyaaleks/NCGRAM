import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../service/user-service";
import {PostService} from "../../service/post.service";
import {Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ClaimModel} from "../../Model/claim-model";

@Component({
  selector: 'app-dialogwindow',
  templateUrl: './dialogwindow.component.html',
  styleUrls: ['./dialogwindow.component.css']
})
export class DialogwindowComponent implements OnInit {
  public report: FormGroup;
  constructor(private userService:UserService,
              private postService:PostService,
              private router: Router,
              public dialogRef: MatDialogRef<DialogwindowComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {this.report = new FormGroup({
    text: new FormControl('',[Validators.required])
  })
  ;
  }
  public sendReport(){
    let claim:ClaimModel={
      id:null,
    postId:this.data.post.postId,
    date:new Date(),
    reason:this.report.controls['text'].value,
    status:"Active",
    authorId:this.data.post.authorId
    }
    this.postService.sendClaim(claim).subscribe();
  }

}
