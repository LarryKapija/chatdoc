package com.amaury.chatdoc.controllers.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "EmptyResponse")
class EmptyResponse {
    @ApiModelProperty("type")
    String type
    @ApiModelProperty("message")
    String message
}
