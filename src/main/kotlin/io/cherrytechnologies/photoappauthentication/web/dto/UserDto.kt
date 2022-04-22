package io.cherrytechnologies.photoappauthentication.web.dto

import io.cherrytechnologies.photoappauthentication.domain.Users
import java.util.*

data class UserDto(
    var id: UUID?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val password: String?,
) {
    fun toUser() = with(this) {
        Users(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
        )
    }
}