import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserModel} from "../../Model/userModel";
import {UserService} from "../../service/user-service";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  public authForm: FormGroup;


  constructor(private userService:UserService,
              private router: Router,
              private authService:AuthService) { }


  ngOnInit() {
    this.authForm=new FormGroup({
      username:new FormControl('',Validators.required),
      password:new FormControl('',Validators.required),
    })
  }


  public authUser()
  {
  this.authService.login(this.authForm.controls['username'].value,this.authForm.controls['password'].value);

    // user;
    // if(user==null)
    // {
    //   this.router.navigate(['/home']);
    // }
    // else
    // {
    //   this.router.navigate(['/story']);
    // }


  }


}
