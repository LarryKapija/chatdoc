package com.amaury.chatdoc.controllers.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "ValidateOtpRequest", description = "OTP to validate")
class ValidateOtpRequest {
    @ApiModelProperty(value = "userName", dataType = "string")
    String userName
    @ApiModelProperty(value = "otp", dataType = "string")
    String otp
}
