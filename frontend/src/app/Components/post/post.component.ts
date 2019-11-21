import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {DialogwindowComponent} from '../dialogwindow/dialogwindow.component';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  src = 'assets/img/Image00008-4.jpg';
  hashTags = ['#batyaSnimaet', '#VitaliyOrechov'];
  description = 'Всем привет, сегодня снимаем новую сцену';

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
