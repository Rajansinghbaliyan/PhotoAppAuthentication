package io.cherrytechnologies.photoappauthentication.web.services

import io.cherrytechnologies.photoappauthentication.customexceptions.BadRequestException
import io.cherrytechnologies.photoappauthentication.web.repositories.UserRepository
import org.springframework.stereotype.Service
import io.cherrytechnologies.photoappauthentication.customexceptions.NotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Service
class LoginService(val userRepository: UserRepository, val passwordEncoder: BCryptPasswordEncoder) {

    fun authenticateByEmail(email: String, password: String): String {
        val userDb = userRepository.findByEmail(email)
            ?: throw NotFoundException("user with email: $email is not present")

        return when (passwordEncoder.matches(password, userDb.password)) {
            true -> "user is authenticated"
            false -> throw BadRequestException("please enter the correct password")
        }
    }
}