import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {DialogwindowComponent} from '../dialogwindow/dialogwindow.component';
import {UserService} from "../../service/user-service";
import {UserModel} from "../../Model/userModel";

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
  private activeUser:boolean;

  constructor(public dialog: MatDialog,
              private userService:UserService) {

  }
  getUrl() {
    if(this.authorPhotoPath!=null) {
      return `url(\'http://localhost:8083/api/photo/${this.authorPhotoPath}\')`;
    }
  }
  getPostUrl()
  {
    if(this.src!=null) {
      return "http://localhost:8083/api/photo/" + this.src;
    }
  }

  get authorId() {
    return "user/"+this._authorId;
  }

  openDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '30%';
    this.dialog.open(DialogwindowComponent, dialogConfig);
  }

  get hashTags() {
    return this._hashTags.id;
  }

  ngOnInit() {
    this.userService.activeUser.subscribe((user:UserModel)=>{
      if(this.authorId!=null) {
        if(user.id === this._authorId) {
          this.activeUser = true;
        }
        else
        {
          this.activeUser=false;
        }
      }

    })
  }
}
