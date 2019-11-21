import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {PostComponent} from './Components/post/post.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {StoryMenuComponent} from './Components/story-menu/story-menu.component';
import {StoryItemComponent} from './Components/story-item/story-item.component';
import {NavigationComponent} from './Components/navigation/navigation.component';
import {AboutUserComponent} from './Components/about-user/about-user.component';
import {PublicationComponent} from './Components/publication/publication.component';
import {MainComponent} from './Components/main/main.component';
import {ActivityComponent} from './Components/activity/activity.component';
import {RegistrationComponent} from './Components/registration/registration.component';
import {RouterModule} from '@angular/router';
import {RoutingmoduleModule} from './routingmodule/routingmodule.module';
import {CommentComponent} from './Components/comment/comment.component';
import {HttpClientModule} from '@angular/common/http';
import {ModalModule} from 'ngx-bootstrap/modal';
import {MAT_DIALOG_DEFAULT_OPTIONS, MatDialogModule} from '@angular/material';
import {DialogwindowComponent} from './Components/dialogwindow/dialogwindow.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NotificationsComponent} from './Components/notifications/notifications.component';
import {UserService} from "./service/user-service";
import {PostService} from "./service/post.service";

@NgModule({
  entryComponents: [DialogwindowComponent, NotificationsComponent],
  declarations: [
    AppComponent,
    PostComponent,
    StoryMenuComponent,
    StoryItemComponent,
    NavigationComponent,
    AboutUserComponent,
    PublicationComponent,
    MainComponent,
    ActivityComponent,
    RegistrationComponent,
    CommentComponent,
    AboutUserComponent,
    DialogwindowComponent,
    NotificationsComponent
  ],
  imports: [
    BrowserModule, FormsModule, RoutingmoduleModule, ReactiveFormsModule,
    HttpClientModule,
    ModalModule.forRoot(),
    MatDialogModule,
    BrowserAnimationsModule
  ],
  providers: [{provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}, UserService,PostService],
  bootstrap: [AppComponent],
  exports: [MatDialogModule]
})
export class AppModule {
}
