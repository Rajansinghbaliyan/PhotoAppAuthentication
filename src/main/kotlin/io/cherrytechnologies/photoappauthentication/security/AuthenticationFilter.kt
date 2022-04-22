package io.cherrytechnologies.photoappauthentication.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.cherrytechnologies.photoappauthentication.customexceptions.InternalErrorException
import io.cherrytechnologies.photoappauthentication.web.models.AuthenticateModel
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter : UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        try {
            val cred = ObjectMapper().readValue(request?.inputStream, AuthenticateModel::class.java)
            return authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    cred.email,
                    cred.password,
                    listOf()
                )
            )
        } catch (e: IOException) {
            throw InternalErrorException(e.message)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        super.successfulAuthentication(request, response, chain, authResult)
    }
}