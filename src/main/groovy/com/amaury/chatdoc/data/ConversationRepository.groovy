package com.amaury.chatdoc.data

import com.amaury.chatdoc.data.entity.Conversation
import com.amaury.chatdoc.data.entity.key.ConversationId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConversationRepository extends JpaRepository<Conversation, ConversationId> {
}