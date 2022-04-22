package io.cherrytechnologies.photoappauthentication.web.models

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class AuthenticateModel(
    @field:NotNull(message = "email can't be null")
    @field:Email
    val email:String,
    @field:NotNull(message = "please provide password")
    @field:Size(min = 8, message = "please provide correct password")
    val password:String)