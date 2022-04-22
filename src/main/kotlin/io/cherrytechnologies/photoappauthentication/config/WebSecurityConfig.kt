package io.cherrytechnologies.photoappauthentication.config

import io.cherrytechnologies.photoappauthentication.utils.globalLogInfo
import io.cherrytechnologies.photoappauthentication.utils.logInfo
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Value("\${gateway.ip}")
    var gateWayIp: String? = null

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()
        http?.cors()?.disable()

        http?.authorizeRequests()
            ?.antMatchers("/**")
            ?.hasIpAddress(gateWayIp)
            ?.globalLogInfo("The gateway ip: $gateWayIp")

    }
}