import { Component, OnInit } from '@angular/core';
import {ConversationService} from '../conversation-service/conversation.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-chat-create-page',
  templateUrl: './chat-create-page.component.html',
  styleUrls: ['./chat-create-page.component.scss']
})
export class ChatCreatePageComponent implements OnInit {

  constructor(private conversationService: ConversationService,
              private router: Router) { }

  ngOnInit() {
  }

  createConversation(name: string, membersText: string) {
    const members = membersText.split(', ');
    this.conversationService.createNewConversation(name, members).subscribe((response) => {
      this.router.navigate(['/chat']);
    });
  }

}
