package com.paulpantea.chat.repositories;

import com.paulpantea.chat.core.Conversation;
import com.paulpantea.chat.core.ConversationMember;
import com.paulpantea.chat.core.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationMemberRepository extends JpaRepository<ConversationMember, UUID> {

    List<ConversationMember> getAllByMember(Member member);
    List<ConversationMember> getAllByConversation(Conversation conversation);

}