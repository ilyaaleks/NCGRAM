import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  @Input()
  author;
  @Input()
  date;
  @Input()
  src
  @Input()
  id;
  @Input()
  authorPhotoPath;
  constructor() { }

  ngOnInit() {
  }
  getUrl() {
    if(this.users!=null && path!=null) {
      return `url(\'http://localhost:8083/api/photo/${path}\')`;
    }
  }
}
