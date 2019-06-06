import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';
import {urls} from '../../../../config/urls';

@Injectable({
  providedIn: 'root'
})
export class ConversationService {

  constructor(private http: HttpClient) { }

  createNewConversation(name: string, members: string[]) {
    return this.http.post(environment.apiURL + urls.createNewConversation, {
      name,
      members
    });
  }

  getConversations() {
    return this.http.get<string[]>(environment.apiURL + urls.getConversations);
  }

  getConversationDetails(conversation: string) {
    return this.http.get(environment.apiURL + urls.getConversationInfo + '?conversation=' + conversation);
  }

  getMessages(conversation: string) {
    return this.http.get<any[]>(environment.apiURL + urls.getMessages + '?conversation=' + conversation);
  }

  addMessage(conversation: string, text: string) {
    return this.http.get(environment.apiURL + urls.addMessage + '?conversation=' + conversation + '&text=' + text);
  }

}
