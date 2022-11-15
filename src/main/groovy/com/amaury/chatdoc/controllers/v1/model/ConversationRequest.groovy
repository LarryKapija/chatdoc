package com.amaury.chatdoc.controllers.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "ConversationRequest", description = "Conversation Request")
class ConversationRequest {
    @ApiModelProperty("withUser")
    String withUser
}
