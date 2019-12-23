import {Component, Inject, OnInit} from '@angular/core';
import {UserModel} from "../../Model/userModel";
import {UserService} from "../../service/user-service";
import {MAT_DIALOG_DATA} from "@angular/material";
import {UserPageDto} from "../../Model/user-page-dto";

@Component({
  selector: 'app-subscribtion-window',
  templateUrl: './subscribtion-window.component.html',
  styleUrls: ['./subscribtion-window.component.css']
})
export class SubscribtionWindowComponent implements OnInit {

  private users: UserModel[];
  itemsPerPage: number = 5;
  totalItems: any;
  page: any;
  previousPage: any;
  private userId: number;

  constructor(private userService: UserService,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.userId = data.idUser;
  }

  ngOnInit() {
    this.userService.getUserSubscriptions(0, this.userId).subscribe((userDto: UserPageDto) => {
      this.users = userDto.users;
      this.totalItems = userDto.totalPage * this.itemsPerPage;
      this.page = userDto.currentPage + 1;
    })
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.userService.getUserSubscriptions(page - 1, this.userId).subscribe(
        (userDto: UserPageDto) => {
          this.users = userDto.users;
          this.totalItems = userDto.totalPage * this.itemsPerPage;
          this.page = userDto.currentPage + 1;
        }
      );

    }

  }

  getUrl(path:string) {
    if(this.users!=null && path!=null) {
      return `url(\'http://localhost:8083/api/photo/${path}\')`;
    }
  }

}
