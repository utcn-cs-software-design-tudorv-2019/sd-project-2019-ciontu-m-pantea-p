package com.paulpantea.chat.web.controllers;

import com.paulpantea.chat.core.Conversation;
import com.paulpantea.chat.core.Member;
import com.paulpantea.chat.services.ConversationService;
import com.paulpantea.chat.services.MemberService;
import com.paulpantea.chat.web.requests.CreateConversationRequest;
import com.paulpantea.chat.web.responses.ConversationInfoResponse;
import com.paulpantea.chat.web.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    private MemberService memberService;
    private ConversationService conversationService;

    @Autowired
    public ConversationController(MemberService memberService, ConversationService conversationService) {
        this.memberService = memberService;
        this.conversationService = conversationService;
    }

    @GetMapping("/all")
    public ResponseEntity getAllConversationsForMember(Principal principal) {

        Member member = this.memberService.getMemberByUsername(principal.getName());

        List<UUID> result = this.conversationService.getAllConversationsByMember(member);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity createNewConversation(Principal principal, @RequestBody CreateConversationRequest request) {

        Member me = this.memberService.getMemberByUsername(principal.getName());

        List<Member> members = this.memberService.getMembersByNames(request.getMembers());
        members.add(me);

        UUID conversationUUID =  this.conversationService.createConversation(request.getName(), members);

        this.simpMessagingTemplate.convertAndSend("/events/update", conversationUUID);

        return new ResponseEntity(conversationUUID, HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity getConversationInfo(Principal principal, String conversation) {

        UUID conversationUUID = UUID.fromString(conversation);
        Member me = this.memberService.getMemberByUsername(principal.getName());

        Conversation conv = this.conversationService.getConversationByUUID(conversationUUID);

        if (!this.conversationService.doesConversationContainMember(conv, me)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        ConversationInfoResponse response = new ConversationInfoResponse();
        response.setUuid(conv.getUuid());
        response.setName(conv.getName());
        response.setNameList(this.conversationService.getMemberListString(conv, 30));

        return new ResponseEntity(response, HttpStatus.OK);

    }

    @GetMapping("/messages")
    public ResponseEntity getConversationMessages(Principal principal, String conversation) {

        UUID conversationUUID = UUID.fromString(conversation);
        Member me = this.memberService.getMemberByUsername(principal.getName());

        Conversation conv = this.conversationService.getConversationByUUID(conversationUUID);

        if (!this.conversationService.doesConversationContainMember(conv, me)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        List<MessageResponse> response = this.conversationService.getMessages(conv);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/message")
    public ResponseEntity getConversationMessages(Principal principal, String conversation, String text) {

        UUID conversationUUID = UUID.fromString(conversation);
        Member me = this.memberService.getMemberByUsername(principal.getName());

        Conversation conv = this.conversationService.getConversationByUUID(conversationUUID);

        if (!this.conversationService.doesConversationContainMember(conv, me)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        this.conversationService.addMessage(conv, me, text);
        this.simpMessagingTemplate.convertAndSend("/events/" + conv.getUuid() + "/update", conv.getUuid());

        return new ResponseEntity(HttpStatus.OK);
    }

}
