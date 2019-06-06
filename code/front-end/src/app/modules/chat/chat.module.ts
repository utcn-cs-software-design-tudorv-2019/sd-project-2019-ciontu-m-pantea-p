import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChatRoutingModule } from './chat-routing.module';
import { ChatPageComponent } from './chat-page/chat-page.component';
import { ChatCreatePageComponent } from './chat-create-page/chat-create-page.component';
import { ConversationTagComponent } from './conversation-tag/conversation-tag.component';
import { ChatScreenComponent } from './chat-screen/chat-screen.component';
import {VirtualScrollerModule} from 'ngx-virtual-scroller';

@NgModule({
  declarations: [ChatPageComponent, ChatCreatePageComponent, ConversationTagComponent, ChatScreenComponent],
  imports: [
    CommonModule,
    ChatRoutingModule,
    VirtualScrollerModule,
  ]
})
export class ChatModule { }
