import {Component, OnInit} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {DialogwindowComponent} from '../dialogwindow/dialogwindow.component';
import {NotificationsComponent} from '../notifications/notifications.component';
import {UserService} from "../../service/user-service";
import {UserModel} from "../../Model/userModel";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(public dialog: MatDialog,
              public userService:UserService,
              private authService:AuthService,
              private router: Router) {
  }

  ngOnInit() {
  }

  openDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '50%';
    this.dialog.open(NotificationsComponent, dialogConfig);
  }
  openAboutUser()
  {
    this.userService.activeUser.subscribe((user:UserModel)=>
    {
      this.router.navigate(['/user/'+user.id]);
    })
  }
  signout()
  {
    this.authService.logout();
  }
}
