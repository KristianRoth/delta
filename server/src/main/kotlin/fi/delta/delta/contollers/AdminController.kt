package fi.delta.delta.contollers

import fi.delta.delta.services.PingResponse
import fi.delta.delta.services.SignUpRequest
import fi.delta.delta.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import java.nio.charset.CharsetEncoder


@RestController
@RequestMapping("/admin", produces = ["application/json"])
class AdminController @Autowired constructor(
        private val userService: UserService
){
    @GetMapping("/ping")
    fun hello(): PingResponse {
        return PingResponse()
    }



}