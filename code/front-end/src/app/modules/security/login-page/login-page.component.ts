import { Component, OnInit } from '@angular/core';
import {AdminAuthService} from '../admin-auth-service/admin-auth.service';
import {Router} from '@angular/router';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  error: string;

  constructor(private adminAuthService: AdminAuthService,
              private router: Router) { }

  ngOnInit() {
  }

  login(nickname: string, password: string) {
    this.adminAuthService.login(nickname, password)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['/chat']);
        },
        error => {
          console.error(error);
          this.error = 'Password seems to be incorrect!';
          // this.loading = false;
        });
  }

}
