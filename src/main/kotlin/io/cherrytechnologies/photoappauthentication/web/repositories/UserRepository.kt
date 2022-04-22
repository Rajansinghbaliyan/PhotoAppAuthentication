package io.cherrytechnologies.photoappauthentication.web.repositories

import io.cherrytechnologies.photoappauthentication.domain.Users
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<Users,UUID> {
    fun findByEmail(email: String) : Users?
}