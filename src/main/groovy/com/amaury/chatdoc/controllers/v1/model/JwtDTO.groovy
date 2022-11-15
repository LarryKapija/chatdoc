package com.amaury.chatdoc.controllers.v1.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Jwt", description = "Jwt Data")
class JwtDTO {

    @ApiModelProperty(value = "token", dataType = "string")
    String token
    @ApiModelProperty(value = "name", dataType = "string")
    String name
    @ApiModelProperty(value = "email", dataType = "string")
    String email
}
