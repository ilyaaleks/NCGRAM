import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserModel} from "../../Model/userModel";
import {UserService} from "../../service/user-service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  public authForm: FormGroup;
  constructor(private userService:UserService) { }
  private confirmedUser:UserModel;
  ngOnInit() {
    this.authForm=new FormGroup({
      username:new FormControl('',Validators.required),
      password:new FormControl('',Validators.required),
    })
  }
  public authUser()
  {
    let user:UserModel={
      id:null,
    name:null,
    surname:null,
    aboutMe:null,
    login:this.authForm.controls['username'].value,
    password:this.authForm.controls['password'].value,
    role:null,
    status:null,
    photoUrl:null,
    email:null,
    }
  this.userService.getAuthUser(this.authForm.controls['username'].value,this.authForm.controls['password'].value).subscribe((user:UserModel)=>
  {
    this.confirmedUser=user;
  });
    if(user==null)
    {
      //.....
    }
    else
    {
      //.....
    }
  }


}
