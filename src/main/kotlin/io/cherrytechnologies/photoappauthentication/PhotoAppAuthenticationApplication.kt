package io.cherrytechnologies.photoappauthentication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class PhotoAppAuthenticationApplication

fun main(args: Array<String>) {
    runApplication<PhotoAppAuthenticationApplication>(*args)
}
