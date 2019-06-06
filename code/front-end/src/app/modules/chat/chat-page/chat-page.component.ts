import { Component, OnInit } from '@angular/core';
import {AdminAuthService} from '../../security/admin-auth-service/admin-auth.service';
import {Router} from '@angular/router';
import {ConversationService} from '../conversation-service/conversation.service';
import {RxStompService} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';

@Component({
  selector: 'app-chat-page',
  templateUrl: './chat-page.component.html',
  styleUrls: ['./chat-page.component.scss']
})
export class ChatPageComponent implements OnInit {

  myname: string;
  conversationIDs: string[];

  selectedConversationID: string;
  messages: any[] = [];

  socketHandler: any;
  socketHandler2: any;

  constructor(private adminAuthService: AdminAuthService,
              private conversationService: ConversationService,
              private rxStompService: RxStompService,
              private router: Router) { }

  ngOnInit() {
    this.myname = this.adminAuthService.currentUserValue.username;
    this.conversationService.getConversations().subscribe((response: string[]) => {
      this.conversationIDs = response;
      if (this.conversationIDs.length > 0) {
        this.selectConversation(this.conversationIDs[0]);
      }
    });
    this.socketHandler2 = this.rxStompService.watch('/events/update')
      .subscribe((message: Message) => {
        this.conversationService.getConversations().subscribe((response: string[]) => {
          this.conversationIDs = response;
        });
      });
  }

  logout() {
    this.adminAuthService.logout();
    this.router.navigate(['/security']);
  }

  onEnter(textBox: any) {
    if (!this.selectedConversationID) {
      return;
    }
    this.conversationService.addMessage(this.selectedConversationID, textBox.value).subscribe((response) => {
      textBox.value = '';
    });
  }

  createConversation() {
    this.router.navigate(['/chat/create']);
  }

  selectConversation(conversationID: string) {
    this.selectedConversationID = conversationID;
    this.messages = [];
    this.updateMessages();
    if (this.socketHandler) {
      this.socketHandler.unsubscribe();
    }
    this.socketHandler = this.rxStompService.watch('/events/' + this.selectedConversationID + '/update')
      .subscribe((message: Message) => {
        this.updateMessages();
      });
  }

  updateMessages() {
    this.conversationService.getMessages(this.selectedConversationID).subscribe((response) => {
      this.messages = response;
    });
  }

  getPersonClass(otherName: string) {
    return otherName === this.myname ? 'message-own' : 'message-other';
  }

}
