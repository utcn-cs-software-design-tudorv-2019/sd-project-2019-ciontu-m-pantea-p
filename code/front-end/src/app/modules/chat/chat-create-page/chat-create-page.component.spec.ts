import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatCreatePageComponent } from './chat-create-page.component';

describe('ChatCreatePageComponent', () => {
  let component: ChatCreatePageComponent;
  let fixture: ComponentFixture<ChatCreatePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChatCreatePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChatCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
