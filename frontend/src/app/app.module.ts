import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {PostComponent} from './post/post.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {StoryMenuComponent} from './story-menu/story-menu.component';
import {StoryItemComponent} from './story-item/story-item.component';
import {NavigationComponent} from './navigation/navigation.component';
import {AboutUserComponent} from './about-user/about-user.component';
import {PublicationComponent} from './publication/publication.component';
import {MainComponent} from './main/main.component';
import {ActivityComponent} from './activity/activity.component';
import {RegistrationComponent} from './registration/registration.component';
import {RouterModule} from '@angular/router';
import {RoutingmoduleModule} from './routingmodule/routingmodule.module';
import {CommentComponent} from './comment/comment.component';
import {HttpClientModule} from '@angular/common/http';
import {ModalModule} from 'ngx-bootstrap/modal';
import {MAT_DIALOG_DEFAULT_OPTIONS, MatDialogModule} from '@angular/material';
import {DialogwindowComponent} from './dialogwindow/dialogwindow.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NotificationsComponent} from './notifications/notifications.component';
import {UserService} from "./service/user-service";

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
  providers: [{provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}, UserService],
  bootstrap: [AppComponent],
  exports: [MatDialogModule]
})
export class AppModule {
}
