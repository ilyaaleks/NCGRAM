import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-story-menu',
  templateUrl: './story-menu.component.html',
  styleUrls: ['./story-menu.component.css']
})
export class StoryMenuComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

  getUrl() {
    return 'url(\'http://localhost:4200/assets/img/1508101437141939217.jpg\')';
  }
}
