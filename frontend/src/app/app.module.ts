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
import {RoutingmoduleModule} from './routingmodule/routingmodule.module';
import {CommentComponent} from './Components/comment/comment.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ModalModule} from 'ngx-bootstrap/modal';
import {MAT_DIALOG_DEFAULT_OPTIONS, MatDialogModule} from '@angular/material';
import {DialogwindowComponent} from './Components/dialogwindow/dialogwindow.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NotificationsComponent} from './Components/notifications/notifications.component';
import {UserService} from "./service/user-service";
import {PostService} from "./service/post.service";
import {ScrollingModule} from "@angular/cdk/scrolling";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {InterceptorService} from "./service/interceptor.service";
import {JwtInterceptor} from "./service/jwt-interceptor";
import { SubscribtionWindowComponent } from './Components/subscribtion-window/subscribtion-window.component';
import { SubscribersWindowComponent } from './Components/subscribers-window/subscribers-window.component';

@NgModule({
  entryComponents: [DialogwindowComponent, NotificationsComponent,SubscribtionWindowComponent,
    SubscribersWindowComponent],
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
    NotificationsComponent,
    SubscribtionWindowComponent,
    SubscribersWindowComponent,
  ],
  imports: [
    BrowserModule, FormsModule, RoutingmoduleModule, ReactiveFormsModule,
    HttpClientModule,
    ModalModule.forRoot(),
    MatDialogModule,
    BrowserAnimationsModule,
    NgbModule
  ],
  providers: [{provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}, UserService,PostService,{
    provide: HTTP_INTERCEPTORS,
    useClass: InterceptorService,
    multi: true
  },JwtInterceptor],
  bootstrap: [AppComponent],
  exports: [MatDialogModule,ScrollingModule]
})
export class AppModule {
}
