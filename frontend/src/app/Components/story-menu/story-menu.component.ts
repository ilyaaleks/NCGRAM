import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user-service";
import {UserModel} from "../../Model/userModel";

@Component({
  selector: 'app-story-menu',
  templateUrl: './story-menu.component.html',
  styleUrls: ['./story-menu.component.css']
})
export class StoryMenuComponent implements OnInit {
  private userPhoto:string;
  private login:string;
  private name;
  constructor(private userService:UserService) {

  }

  ngOnInit() {
    this.userService.activeUser.subscribe((user:UserModel)=>
    {
      this.userPhoto=user.photoUrl;
      this.login=user.login;
      this.name=user.name+" "+user.surname;
    })
  }
  getUrl() {
    return `url(\'http://localhost:8083/api/photo/${this.userPhoto}\')`;
  }
}
