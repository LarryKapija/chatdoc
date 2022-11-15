package com.amaury.chatdoc.controllers.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "User", description = "User Data")
class UserDTO {
    @ApiModelProperty(value = "name", dataType = "string")
    String name
    @ApiModelProperty(value = "email", dataType = "string")
    String email
    @ApiModelProperty(value = "photoUrl", dataType = "string")
    String photoUrl
    @ApiModelProperty(value = "photoUrl", dataType = "array")
    List<ConversationDTO> conversations
}
