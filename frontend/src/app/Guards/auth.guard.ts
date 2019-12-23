import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from "../service/auth.service";
import {UserService} from "../service/user-service";
import {UserModel} from "../Model/userModel";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor( private router: Router,
               private userService:UserService) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      let token=localStorage.getItem("token");
      if(token!=null)
      {
        return true;
      }
      else
      {
        this.router.navigate(['/home'], { queryParams: { returnUrl: state.url } });
        return false;
      }
    };

}
