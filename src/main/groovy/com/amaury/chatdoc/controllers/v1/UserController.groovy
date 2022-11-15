package com.amaury.chatdoc.controllers.v1

import com.amaury.chatdoc.controllers.model.EmptyResponse
import com.amaury.chatdoc.controllers.v1.model.ConversationRequest
import com.amaury.chatdoc.controllers.v1.model.UserDTO
import com.amaury.chatdoc.controllers.v1.model.UserRequest
import com.amaury.chatdoc.controllers.v1.model.UserResponse
import com.amaury.chatdoc.controllers.v1.model.UsersResponse
import com.amaury.chatdoc.controllers.v1.model.ValidationResponse
import com.amaury.chatdoc.data.entity.User
import com.amaury.chatdoc.exception.NotFound
import com.amaury.chatdoc.mapper.UserDtoMapper
import com.amaury.chatdoc.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(tags = ["User"])
class UserController {

    @Autowired
    UserService userService

    @Autowired
    UserDtoMapper dtoMapper;

    @ApiOperation(value = "Create a new profile")
    @PostMapping("/create-profile")
    ResponseEntity<EmptyResponse> createProfile(@RequestBody UserRequest request) {
        new ResponseEntity<EmptyResponse>(new EmptyResponse(type: "OK", message: "Profile created"), HttpStatus.OK)
    }

    @ApiOperation(value = "Update an existing profile")
    @PatchMapping("/update-profile")
    ResponseEntity<EmptyResponse> updateProfile(@RequestBody UserRequest request) {
        String userName = userService.getCurrentUser()
        return userService.getUser(userName).map(user -> {
            user.setEmail(request.getEmail())
            userService.updateUser(user)
            new ResponseEntity<EmptyResponse>(new EmptyResponse(type: "OK", message: "Profile updated"), HttpStatus.OK)
        }).orElseThrow(() -> {throw new NotFound("Username ${userName} not found")})
    }

    @ApiOperation(value = "Verify if an user name is already taken")
    @GetMapping("/check-user-name/{userName}")
    ResponseEntity<ValidationResponse> checkUserName(@PathVariable(value = "userName") String userName) {
        boolean exists = userService.getUser(userName).isPresent()
        String message = exists ? "username already taken" : "user name is available"

        new ResponseEntity<ValidationResponse>(new ValidationResponse(value: exists, type: "OK", message: message), HttpStatus.OK)
    }

    @ApiOperation(value = "Get Current User")
        @GetMapping("/get-current-user")
    ResponseEntity<UserResponse> getUsers() {

        userService.getUser(userService.getCurrentUser()).map(user -> {
            UserDTO dto = dtoMapper.build(user)
            new ResponseEntity<UserResponse>(new UserResponse(value: dto, type: "OK", message: "OK"), HttpStatus.OK)
        })
        .orElseThrow(() -> new NotFound("User not found"))


    }

    @ApiOperation(value = "Start a new conversation")
    @PostMapping("/start-new-conversation")
    ResponseEntity<EmptyResponse> startNewConversation(@RequestBody ConversationRequest request) {
        userService.startNewConversation(userService.getCurrentUser(), request.getWithUser())
        new ResponseEntity<EmptyResponse>(new EmptyResponse(type: "OK", message: "Conversation started"), HttpStatus.OK)
    }

}
