package io.cherrytechnologies.photoappauthentication.security

import io.cherrytechnologies.photoappauthentication.utils.globalLogInfo
import io.cherrytechnologies.photoappauthentication.web.services.LoginService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    val loginService: LoginService,
    val passwordEncoder: BCryptPasswordEncoder
) : WebSecurityConfigurerAdapter() {

    @Value("\${gateway.ip}")
    var gateWayIp: String? = null

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()
        http?.cors()?.disable()

        http?.authorizeRequests()
            ?.antMatchers("/**")
            ?.hasIpAddress(gateWayIp)
            ?.globalLogInfo("The gateway ip: $gateWayIp")
            ?.and()
            ?.addFilter(getAuthenticationFilter())

    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth
            ?.userDetailsService(loginService)
            ?.passwordEncoder(passwordEncoder)
    }

    fun getAuthenticationFilter() = with(AuthenticationFilter()) {
        this.setAuthenticationManager(authenticationManager())
        this
    }
}