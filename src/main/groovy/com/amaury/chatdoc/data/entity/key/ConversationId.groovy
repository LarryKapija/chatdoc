package com.amaury.chatdoc.data.entity.key

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

class ConversationId implements Serializable {
    @Column(name = "ID_KEY")
    String key
    @Column(name = "MAIN_USER")
    String mainUser
}
