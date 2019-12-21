import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {DialogwindowComponent} from '../dialogwindow/dialogwindow.component';

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
  @Input()
  hashTags;
  @Input()
  src
  @Input()
  id;
  @Input()
  authorPhotoPath;



  constructor(public dialog: MatDialog) {

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
  openDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '30%';
    this.dialog.open(DialogwindowComponent, dialogConfig);
  }

  ngOnInit() {
  }
}
