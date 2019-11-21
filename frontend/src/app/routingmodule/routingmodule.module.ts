import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from '../Components/main/main.component';
import {AboutUserComponent} from '../Components/about-user/about-user.component';
import {RegistrationComponent} from '../Components/registration/registration.component';
import {StoryMenuComponent} from '../Components/story-menu/story-menu.component';
import {ActivityComponent} from '../Components/activity/activity.component';

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
