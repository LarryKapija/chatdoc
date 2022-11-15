package com.amaury.chatdoc.data.entity

import com.amaury.chatdoc.data.entity.key.ConversationId

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

@Entity
@IdClass(ConversationId.class)
@Table(name = "CONVERSATION")
class Conversation {
    @Id
    String key
    @Id
    String mainUser
    @Column(name = "WITH_USER")
    String withUser
}
