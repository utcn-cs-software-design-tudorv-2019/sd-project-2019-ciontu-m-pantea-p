package com.paulpantea.chat.repositories;

import com.paulpantea.chat.core.Conversation;
import com.paulpantea.chat.core.ConversationMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationMessageRepository extends JpaRepository<ConversationMessage, UUID> {

    List<ConversationMessage> getAllByConversation(Conversation conversation);

}