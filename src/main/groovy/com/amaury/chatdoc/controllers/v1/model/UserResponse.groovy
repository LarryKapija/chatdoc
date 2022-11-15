package com.amaury.chatdoc.controllers.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "UserResponse", description = "User Response")
class UserResponse {
    @ApiModelProperty("type")
    String type
    @ApiModelProperty("message")
    String message
    @ApiModelProperty("value")
    UserDTO value
}
