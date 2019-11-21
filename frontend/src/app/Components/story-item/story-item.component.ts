import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-story-item',
  templateUrl: './story-item.component.html',
  styleUrls: ['./story-item.component.css']
})
export class StoryItemComponent implements OnInit {
  time = '15 минут';
  author = 'batyaOrechov';
  source = 'assets/img/Image00008-4.jpg';

  constructor() {
  }

  ngOnInit() {
  }

}
