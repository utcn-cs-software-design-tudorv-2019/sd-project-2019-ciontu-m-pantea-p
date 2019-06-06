import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {Member} from '../core/Member';
import {map} from 'rxjs/operators';
import {environment} from '../../../../environments/environment';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthService {

  private currentUserSubject: BehaviorSubject<Member>;
  public currentUser: Observable<Member>;

  constructor(private http: HttpClient,
              private router: Router) {
    this.currentUserSubject = new BehaviorSubject<Member>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): Member {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {
    return this.http.post<any>(`${environment.apiURL}/login`, { username, password }, {observe: 'response'})
      .pipe(map(resp => {
        const member = new Member();
        const token = resp.headers.get('Authorization');
        // login successful if there's a jwt token in the response
        if (token) {
          member.username = username;
          member.token = token;
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(member));
          this.currentUserSubject.next(member);
        }

        return member;
      }));
  }

  register(username: string, password: string) {
    return this.http.post<any>(`${environment.apiURL}/members/register`, { username, password }).subscribe((response) => {
      this.router.navigate(['/security']);
    });
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

}
