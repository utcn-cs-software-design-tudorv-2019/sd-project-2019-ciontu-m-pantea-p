import {Component, Input, OnInit} from '@angular/core';
import {ConversationService} from '../conversation-service/conversation.service';

@Component({
  selector: 'app-conversation-tag',
  templateUrl: './conversation-tag.component.html',
  styleUrls: ['./conversation-tag.component.scss']
})
export class ConversationTagComponent implements OnInit {

  @Input()
  conversationID: string;

  conversation: any;

  constructor(private conversationService: ConversationService) { }

  ngOnInit() {
    this.conversationService.getConversationDetails(this.conversationID).subscribe((response) => {
      console.log(response);
      this.conversation = response;
    });
  }

}
