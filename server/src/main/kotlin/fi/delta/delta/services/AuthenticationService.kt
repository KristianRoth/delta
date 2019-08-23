package fi.delta.delta.services

import fi.delta.delta.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class AuthenticationService  {

    @Value("\${app.jwts.secret}")
    lateinit var secretKey: String


    fun authenticate(email: String, password: String): String {

        val claims: HashMap<String, Any?> = HashMap()

        claims["iss"] = "deltaApplication"
        claims["sub"] = "LoginRequest"
        claims["userEmail"] = email



        val jwt: String = Jwts.builder()
                .setHeaderParam("kid", "deltaApplication")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
        println(jwt)

        return jwt

    }
}