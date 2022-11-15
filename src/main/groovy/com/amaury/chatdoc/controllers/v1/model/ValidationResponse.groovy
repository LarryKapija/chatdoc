package com.amaury.chatdoc.controllers.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "User", description = "User Data")
class ValidationResponse {
    @ApiModelProperty("type")
    String type
    @ApiModelProperty("message")
    String message
    @ApiModelProperty("value")
    Boolean value
}
