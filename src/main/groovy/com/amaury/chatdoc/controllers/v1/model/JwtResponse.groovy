package com.amaury.chatdoc.controllers.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "JwtResponse", description = "Jwt Response")
class JwtResponse {
    @ApiModelProperty("type")
    String type
    @ApiModelProperty("message")
    String message
    @ApiModelProperty("value")
    JwtDTO value
}
