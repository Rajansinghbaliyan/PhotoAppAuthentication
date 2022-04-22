package io.cherrytechnologies.photoappauthentication.web.services

import io.cherrytechnologies.photoappauthentication.web.repositories.UserRepository
import org.springframework.stereotype.Service
import io.cherrytechnologies.photoappauthentication.customexceptions.NotFoundException

@Service
class LoginService(val userRepository: UserRepository) {

    fun authenticateByEmail(email: String,password: String)
     = userRepository.findByEmail(email)
        ?: throw NotFoundException("no user found with this email.")
}