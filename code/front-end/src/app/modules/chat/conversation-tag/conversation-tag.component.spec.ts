import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversationTagComponent } from './conversation-tag.component';

describe('ConversationTagComponent', () => {
  let component: ConversationTagComponent;
  let fixture: ComponentFixture<ConversationTagComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConversationTagComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConversationTagComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
