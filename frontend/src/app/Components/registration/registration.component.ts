import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import {UploadFileService} from "../../service/upload-file.service";
import {UserModel} from "../../Model/userModel";
import {UserService} from "../../service/user-service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  public registrationForm: FormGroup;

  constructor(private fileUploadService:UploadFileService,
              private userService:UserService) {
  }

  ngOnInit() {
    this.registrationForm = new FormGroup({
      userName: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z_]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}')]),
      about: new FormControl('', Validators.required),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      password: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl('', [Validators.required]),
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
  public registerUser():void//можно ли модельки разбивать для регистрация и например для загрузки постов
  {
    console.log("Method work");
    let user:UserModel={
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
    this.userService.saveUser(user).subscribe();
    this.fileUploadService.postFile(this.fileToUpload,user).subscribe(data => {
    }, error => {
      console.log(error);
    });

  }

}
