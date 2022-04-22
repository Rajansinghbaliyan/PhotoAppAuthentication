package io.cherrytechnologies.photoappauthentication.web.controllers

import io.cherrytechnologies.photoappauthentication.utils.logInfo
import io.cherrytechnologies.photoappauthentication.web.models.AuthenticateModel
import io.cherrytechnologies.photoappauthentication.web.services.LoginService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger
import javax.validation.Valid

@RestController
@RequestMapping("/v1/api/login")
class LoginController(val loginService: LoginService) {

    val log: Logger = Logger.getLogger(LoginController::class.toString())

    @PostMapping("/")
    fun authenticateByEmail(
        @Valid
        @RequestBody authenticateModel: AuthenticateModel
    ) = loginService
        .authenticateByEmail(authenticateModel.email,authenticateModel.password)
        .toUserDto()
        .logInfo(log,"/POST authenticate email: ${authenticateModel.email}")
}