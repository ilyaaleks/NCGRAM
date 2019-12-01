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
  author="ilya";
  @Input()
  description;
  @Input()
  date;
  @Input()
  hashTags=["best"];
  @Input()
  src=this.getUrl();
  @Input()
  id;



  constructor(public dialog: MatDialog) {

  }
  getUrl() {
    return 'url(\'http://localhost:4200/assets/img/1508101437141939217.jpg\')';
  }
  openDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '30%';
    this.dialog.open(DialogwindowComponent, dialogConfig);
  }

  ngOnInit() {
  }
}
