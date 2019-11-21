import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  public authForm: FormGroup;
  constructor() { }

  ngOnInit() {
    this.authForm=new FormGroup({
      username:new FormControl('',Validators.required),
      password:new FormControl('',Validators.required),
    })
  }

}
