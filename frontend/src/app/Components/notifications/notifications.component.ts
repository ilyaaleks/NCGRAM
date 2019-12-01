import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {UserModel} from "../../Model/userModel";
import {UserService} from "../../service/user-service";
import {Router} from "@angular/router";
import {PostModel} from "../../Model/postModel";

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {
  public postForm: FormGroup;
  public status:any;
  constructor(private userService:UserService,
              private router: Router) { }

  ngOnInit() {
    this.postForm = new FormGroup({
      text: new FormControl(''),
      tags: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z_]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}')]),
      file: new FormControl(null, [Validators.required])
    },this.passwordAreEquals())
    ;
  }
  private passwordAreEquals(): ValidatorFn {
    return (group: FormGroup): { [key: string]: any } => {
      if (!(group.dirty || group.touched) || group.get('password').value === group.get('confirmPassword').value) {
        return null;
      }
      return {
        custom: 'Password are not equals'
      };
    };
  }
  fileToUpload: File = null;
  public onFileChange(files: FileList)
  {
    this.fileToUpload = files.item(0);
  }
  public addPost():void
  {
    console.log("Method work");
    let post:PostModel={
      id:null,
      name:this.registrationForm.controls['firstName'].value,
      surname:this.registrationForm.controls['lastName'].value,
      aboutMe:this.registrationForm.controls['about'].value,
      login:this.registrationForm.controls['userName'].value,
      password:this.registrationForm.controls['password'].value,
      role:null,
      status:null,
      photoUrl:null,
      email:this.registrationForm.controls['email'].value
    }
    this.userService.saveUser(user,this.fileToUpload).subscribe(()=> {

      },(error1) => {
        this.status=error1;
      }
    );

}
