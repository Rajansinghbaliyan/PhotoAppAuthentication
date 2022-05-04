package io.cherrytechnologies.photoappauthentication.web.controllers

import io.cherrytechnologies.photoappauthentication.utils.responseOk
import io.cherrytechnologies.photoappauthentication.web.services.UserService
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
@RequestMapping("/auth")
class LoginController(val userService: UserService,val env :Environment) {

    val log: Logger = Logger.getLogger(LoginController::class.toString())

    @GetMapping("/status")
    fun getStatus() = "The value of expiration is ${env.getProperty("jwt.expiration")}".responseOk()

}