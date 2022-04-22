package io.cherrytechnologies.photoappauthentication.domain

import io.cherrytechnologies.photoappauthentication.web.dto.UserDto
import java.sql.Timestamp
import java.util.*
import javax.persistence.Entity

@Entity
data class Users(
    override var id: UUID? = null,
    override var createdDate: Timestamp? = null,
    override var lastModifiedDate: Timestamp? = null,
    override var version: Long? = null,
    var firstName: String?,
    var lastName: String?,
    var email: String?,
    var password: String?,
) : Base(id, version, createdDate, lastModifiedDate) {

    fun toUserDto() = with(this) {
        UserDto(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password
        )
    }
}