package com.amaury.chatdoc.controllers.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

import javax.persistence.Column

@ApiModel(value = "UserRequest", description = "User Request")
class UserRequest {
    @ApiModelProperty("name")
    String name
    @ApiModelProperty("email")
    String email
}
