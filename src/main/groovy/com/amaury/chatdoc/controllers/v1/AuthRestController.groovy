package com.amaury.chatdoc.controllers.v1

import com.amaury.chatdoc.controllers.model.EmptyResponse
import com.amaury.chatdoc.controllers.v1.model.JwtDTO
import com.amaury.chatdoc.controllers.v1.model.JwtResponse

import com.amaury.chatdoc.controllers.v1.model.OtpRequest
import com.amaury.chatdoc.controllers.v1.model.UserRequest
import com.amaury.chatdoc.controllers.v1.model.ValidateOtpRequest
import com.amaury.chatdoc.data.entity.User
import com.amaury.chatdoc.exception.InvalidRequest
import com.amaury.chatdoc.exception.Unauthorized
import com.amaury.chatdoc.service.EmailSenderService
import com.amaury.chatdoc.service.UserService
import com.amaury.chatdoc.service.security.UserDetailsImpl
import com.amaury.chatdoc.util.JwtUtils
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthRestController {
    @Autowired
    AuthenticationManager authenticationManager
    @Autowired
    PasswordEncoder encoder
    @Autowired
    EmailSenderService emailSenderService
    @Autowired
    JwtUtils jwtUtils
    @Autowired
    UserService userService

    @ApiOperation(value = "Register New User")
    @PostMapping("/auth/register")
    ResponseEntity<EmptyResponse> registerUser(@RequestBody UserRequest request) {
        if(Objects.isNull(request.getName()))
            throw new InvalidRequest("name cannot be blank")
        if(Objects.isNull(request.getEmail()))
            throw new InvalidRequest("email cannot be blank")

        User user = fromRequest(request)
        user.setPassword(generateOTP().toString())

        userService.createUser(user)
        emailSenderService.sendEmail(user.getEmail(), "OTP Code: ${user.getPassword()}")

        ResponseEntity.ok(new EmptyResponse(type: "OK", message: "OTP was sent to your email"))

    }

    @ApiOperation(value = "Request an OTP to be sent")
    @PostMapping("/auth/sent-otp")
    ResponseEntity<EmptyResponse> sendOtp(@RequestBody OtpRequest request) {
        User user = userService.getUser(request.getUserName())
                .orElseThrow(() -> new Unauthorized("Invalid authentication"))

        user.setPassword(generateOTP().toString())
        userService.updateUser(user)
        emailSenderService.sendEmail(user.getEmail(), "OTP Code: ${user.getPassword()}")

        ResponseEntity.ok(new EmptyResponse(type: "OK", message: "OTP was sent to your email"))
    }

    @ApiOperation(value = "Validate an OTP")
        @PostMapping("/auth/validate-otp")
    ResponseEntity<JwtResponse> validateOTP(@RequestBody ValidateOtpRequest request) {
        User user = userService.getUser(request.getUserName())
                .orElseThrow(() -> new Unauthorized("Invalid authentication"))

        if(user.getPassword() != request.getOtp())
            throw new Unauthorized("Invalid authentication")

        String token = generateJwt(user.getName(), user.getPassword())
        JwtDTO jwt = new JwtDTO(token: token, name: user.getName(), email: user.getEmail())
        ResponseEntity.ok(new JwtResponse(value: jwt, type: "OK", message: "OK"))
    }

    private User fromRequest(UserRequest request) {
        new User(name: request.getName(), email: request.getEmail())
    }


    private String generateJwt(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password))
        SecurityContextHolder.getContext().setAuthentication(authentication)
        jwtUtils.generateJwtToken(authentication)
    }

    private Integer generateOTP(){
        Random random = new Random()
        random.nextInt(899999)+100000
    }
}
