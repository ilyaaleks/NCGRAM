import {Component, Input, OnInit} from '@angular/core';
import {UserService} from "../../service/user-service";
import {CommentService} from "../../service/comment.service";
import {PostModel} from "../../Model/postModel";
import {CommentModel} from "../../Model/comment-model";
import {PostPageDto} from "../../Model/PostPageDto";
import {UserModel} from "../../Model/userModel";
import {CommentPageDto} from "../../Model/comment-page-dto";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Input()
  postId;

  @Input()
  comment;

  constructor(private userService: UserService,
              ) {
  }

  ngOnInit() {

  }

  getUrl(photoPath:string) {
    if (true) {
      return `url(\'http://localhost:8083/api/photo/${photoPath}\')`;
    }
  }


}
