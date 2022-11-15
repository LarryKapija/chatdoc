package com.amaury.chatdoc.mapper

import com.amaury.chatdoc.controllers.v1.model.ConversationDTO
import com.amaury.chatdoc.controllers.v1.model.UserDTO
import com.amaury.chatdoc.data.entity.Conversation
import com.amaury.chatdoc.data.entity.User
import org.springframework.stereotype.Component

@Component
class UserDtoMapper {

    UserDTO build(User entity) {
        new UserDTO(name: entity.getName(),
                email: entity.getEmail(),
                photoUrl: entity.getPhotoUrl(),
                conversations: entity.getConversations().collect {buildConversation(it)}
        )
    }

    ConversationDTO buildConversation(Conversation conversation) {
        new ConversationDTO(key: conversation.getKey(), withUser: conversation.getWithUser())
    }
}
