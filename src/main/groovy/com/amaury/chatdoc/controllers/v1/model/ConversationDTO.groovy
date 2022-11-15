package com.amaury.chatdoc.controllers.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Conversation", description = "Conversation data")
class ConversationDTO {
    @ApiModelProperty("key")
    String key
    @ApiModelProperty("withUser")
    String withUser
}
