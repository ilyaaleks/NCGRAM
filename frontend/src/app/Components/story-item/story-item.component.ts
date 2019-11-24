import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-story-item',
  templateUrl: './story-item.component.html',
  styleUrls: ['./story-item.component.css']
})
export class StoryItemComponent implements OnInit {
  @Input()
  id:number;
  @Input()
  time: string;
  @Input()
  author:string;
  @Input()
  source:string;

  constructor() {
  }

  ngOnInit() {
  }

}
