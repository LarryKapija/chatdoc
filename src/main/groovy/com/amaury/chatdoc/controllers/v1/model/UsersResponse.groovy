package com.amaury.chatdoc.controllers.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "UsersResponse", description = "Users Response")
class UsersResponse {
    @ApiModelProperty("type")
    String type
    @ApiModelProperty("message")
    String message
    @ApiModelProperty("value")
    List<UserDTO> value
}