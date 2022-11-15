package com.amaury.chatdoc.controllers.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "OtpRequest", description = "Request an OTP")
class OtpRequest {
    @ApiModelProperty(value = "userName", dataType = "string")
    String userName
}
