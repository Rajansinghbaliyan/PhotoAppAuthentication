package io.cherrytechnologies.photoappauthentication.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.cherrytechnologies.photoappauthentication.customexceptions.InternalErrorException
import io.cherrytechnologies.photoappauthentication.web.models.AuthenticateModel
import io.cherrytechnologies.photoappauthentication.web.services.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.core.env.Environment
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AuthenticationFilter(
    private val userService: UserService,
    private val env: Environment
) : UsernamePasswordAuthenticationFilter() {

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
        val user = authResult?.principal as User
        val userDb = userService.findByEmail(user.username)
        val token = Jwts.builder()
            .setId(userDb.id.toString())
            .setSubject(userDb.email)
            .setExpiration(
                Date(
                    System.currentTimeMillis() +
                            env.getProperty("jwt.expiration")?.toLong()!!
                )
            )
            .signWith(SignatureAlgorithm.HS512, env.getProperty("jwt.secret"))
            .compact()

        response?.setHeader("token", token)
        response?.setHeader("user-id", userDb.id.toString())
    }
}