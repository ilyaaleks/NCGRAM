import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from '../main/main.component';
import {AboutUserComponent} from '../about-user/about-user.component';
import {RegistrationComponent} from '../registration/registration.component';
import {StoryMenuComponent} from '../story-menu/story-menu.component';
import {ActivityComponent} from '../activity/activity.component';

const appRoutes: Routes = [
  {path: 'home', component: MainComponent},
  {path: 'user/:login', component: AboutUserComponent},
  {path: 'signout', component: MainComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'story', component: ActivityComponent},
  {
    path: '', redirectTo: '/home', pathMatch: 'full'
  },
  {
    path: '**', component: MainComponent
  }

];


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(
      appRoutes, {enableTracing: true}
    )

  ],
  exports: [
    RouterModule
  ]
})
export class RoutingmoduleModule {
}
