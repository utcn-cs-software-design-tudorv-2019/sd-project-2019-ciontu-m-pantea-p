import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AdminAuthService} from '../../admin-auth-service/admin-auth.service';


@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private adminAuthService: AdminAuthService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.adminAuthService.currentUserValue;
    if (currentUser) {
      return true;
    }

    // not logged in so redirect to login page with the return url
    this.router.navigate(['/security']);
    return false;
  }
}
