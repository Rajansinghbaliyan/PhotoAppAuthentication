package io.cherrytechnologies.photoappauthentication.web.controllers

import io.cherrytechnologies.photoappauthentication.web.services.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
@RequestMapping("/v1/api/login")
class LoginController(val userService: UserService) {

    val log: Logger = Logger.getLogger(LoginController::class.toString())

}