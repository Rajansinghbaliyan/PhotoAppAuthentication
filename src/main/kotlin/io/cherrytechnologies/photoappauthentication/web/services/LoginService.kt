package io.cherrytechnologies.photoappauthentication.web.services

import io.cherrytechnologies.photoappauthentication.customexceptions.BadRequestException
import io.cherrytechnologies.photoappauthentication.customexceptions.NotFoundException
import io.cherrytechnologies.photoappauthentication.web.repositories.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginService(val userRepository: UserRepository, val passwordEncoder: BCryptPasswordEncoder) :
    UserDetailsService {

    fun authenticateByEmail(email: String, password: String): String {
        val userDb = userRepository.findByEmail(email)
            ?: throw NotFoundException("user with email: $email is not present")

        return when (passwordEncoder.matches(password, userDb.password)) {
            true -> "user is authenticated"
            false -> throw BadRequestException("please enter the correct password")
        }
    }

    override fun loadUserByUsername(email: String?): UserDetails  {
        val userDb = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("user with email: $email is not present")
        return User(
            userDb.email,
            userDb.password,
            true,
            true,
            true,
            true,
            listOf()
        )
    }
}