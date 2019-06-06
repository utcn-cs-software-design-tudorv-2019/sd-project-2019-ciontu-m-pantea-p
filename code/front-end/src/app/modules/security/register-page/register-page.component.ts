import { Component, OnInit } from '@angular/core';
import {AdminAuthService} from '../admin-auth-service/admin-auth.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {

  constructor(private adminAuthService: AdminAuthService) { }

  ngOnInit() {
  }

  register(nickname: string, password: string) {
    this.adminAuthService.register(nickname, password);
  }

}
