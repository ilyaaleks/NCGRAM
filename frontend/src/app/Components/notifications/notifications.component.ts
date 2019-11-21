import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {

  constructor() { }
  getUrl() {
    return 'url(\'http://localhost:4200/assets/img/Image00008-4.jpg\')';
  }
  getUrl1() {
    return 'url(\'http://localhost:4200/assets/img/1508101437141939217.jpg\')';
  }
  ngOnInit() {
  }

}
