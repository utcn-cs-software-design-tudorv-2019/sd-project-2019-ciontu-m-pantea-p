import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ChatPageComponent} from './chat-page/chat-page.component';
import {AuthGuard} from '../security/utils/guards/auth.guard';
import {ChatCreatePageComponent} from './chat-create-page/chat-create-page.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'create',
        component: ChatCreatePageComponent,
        canActivate: [AuthGuard],
      },
      {
        path: '',
        component: ChatPageComponent,
        canActivate: [AuthGuard],
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChatRoutingModule { }
