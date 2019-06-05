package com.paulpantea.chat.services;

import com.paulpantea.chat.core.Conversation;
import com.paulpantea.chat.core.ConversationMember;
import com.paulpantea.chat.core.ConversationMessage;
import com.paulpantea.chat.core.Member;
import com.paulpantea.chat.repositories.ConversationMemberRepository;
import com.paulpantea.chat.repositories.ConversationMessageRepository;
import com.paulpantea.chat.repositories.ConversationRepository;
import com.paulpantea.chat.web.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConversationService {

    private ConversationRepository conversationRepository;
    private ConversationMemberRepository conversationMemberRepository;
    private ConversationMessageRepository conversationMessageRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository, ConversationMemberRepository conversationMemberRepository, ConversationMessageRepository conversationMessageRepository) {
        this.conversationRepository = conversationRepository;
        this.conversationMemberRepository = conversationMemberRepository;
        this.conversationMessageRepository = conversationMessageRepository;
    }

    public List<UUID> getAllConversationsByMember(Member member) {

        List<ConversationMember> conversationMembers = this.conversationMemberRepository.getAllByMember(member);

        return conversationMembers
                .stream()
                .map(ConversationMember::getConversation)
                .map(Conversation::getUuid)
                .collect(Collectors.toList());

    }

    public UUID createConversation(String name, List<Member> members) {

        Conversation conversation = new Conversation();
        conversation.setName(name);

        this.conversationRepository.save(conversation);

        for (Member member : members) {

            ConversationMember conversationMember = new ConversationMember();
            conversationMember.setConversation(conversation);
            conversationMember.setMember(member);

            this.conversationMemberRepository.save(conversationMember);
        }

        return conversation.getUuid();
    }

    public Conversation getConversationByUUID(UUID conversationUUID) {
        return this.conversationRepository.getOne(conversationUUID);
    }

    public boolean doesConversationContainMember(Conversation conversation, Member member) {

        List<ConversationMember> members = this.conversationMemberRepository.getAllByConversation(conversation);

        for (ConversationMember conversationMember : members) {
            if (conversationMember.getMember().equals(member)) {
                return true;
            }
        }

        return false;
    }

    public String getMemberListString(Conversation conversation, int maxLength) {

        List<ConversationMember> members = this.conversationMemberRepository.getAllByConversation(conversation);
        String result = "";

        for (ConversationMember member : members) {
            result += member.getMember().getUsername() + ", ";
        }

        result = result.substring(0, result.length() - 2);

        if (result.length() > maxLength) {
            result = result.substring(0, maxLength - 3) + "...";
        }

        return result;
    }

    public List<MessageResponse> getMessages(Conversation conv) {

        return this.conversationMessageRepository.getAllByConversation(conv)
                .stream()
                .map(this::messageToResponse)
                .sorted(Comparator.comparing(MessageResponse::getDate).reversed())
                .collect(Collectors.toList());

    }

    private MessageResponse messageToResponse(ConversationMessage conversationMessage) {

        MessageResponse response = new MessageResponse();
        response.setDate(conversationMessage.getDate());
        response.setName(conversationMessage.getMember().getUsername());
        response.setText(conversationMessage.getText());

        return response;
    }

    public void addMessage(Conversation conv, Member me, String text) {

        ConversationMessage conversationMessage = new ConversationMessage();
        conversationMessage.setConversation(conv);
        conversationMessage.setMember(me);
        conversationMessage.setDate(new Date());
        conversationMessage.setText(text);

        this.conversationMessageRepository.save(conversationMessage);
    }
}
