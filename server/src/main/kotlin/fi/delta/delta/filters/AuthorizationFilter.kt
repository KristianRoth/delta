package fi.delta.delta.filters

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.core.annotation.Order
import javax.crypto.SecretKey
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Order(2)
class AuthorizationFilter: Filter
{
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        println("Authentication filter")

        val req: HttpServletRequest = request as HttpServletRequest

        val token = req.getHeader("Authorization")


        println()

        val x = Jwts
                .parser()
                .setSigningKey("wJv2tz6mZ4vYpKX1C3GYAMmvDyDhMCMcm9qK32eYscfgZ7mzEMqTO9oDQdMEa1uCBn5DeFKo1sGaXqzgxJPSsRpDVGRmib8gTfoz")
                .parseClaimsJws(token.replace("Bearer ", ""))

        println(x.body)

        chain?.doFilter(request, response)
    }
}