package fi.delta.delta.services

import fi.delta.delta.models.User
import fi.delta.delta.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.validation.constraints.*


data class GetUserRequest (
    val email: String?,
    val id: Int?
)


data class SignUpRequest(
        @get:NotBlank
        @get:Size(min = 4, message = "Username needs to be over 4 characters")
        val username: String,
        @get:NotBlank
        @get:Email
        val email: String,
        @get:NotBlank
        @get:Size(min = 8, message = "Password needs to be over 8 characters")
        val password: String
)

data class LoginRequest(
        val email: String,
        val password: String
)

data class LoginResponse(
        val jwt: String
)

@Service
class UserService @Autowired constructor (
        private val userRepository: UserRepository,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder,
        private val authenticationService: AuthenticationService
) {

    fun getUserById(userId: Int): User? {

        return userRepository.findById(userId).orElse(null)
    }

    fun getUserByEmail(userEmail: String): User? {
        return userRepository.findByEmail(userEmail)
    }


    fun signUp(signUpRequest: SignUpRequest): LoginResponse {
        val user = User(signUpRequest.email, signUpRequest.username, bCryptPasswordEncoder.encode(signUpRequest.password))

        if (userRepository.existsByEmail(signUpRequest.email) || userRepository.existsByUsername(signUpRequest.username)) {
            println("tet")
            throw CredentialsInUseException("Email or Username in use")
        }

        userRepository.save(user)

        return LoginResponse(authenticationService.authenticate(signUpRequest.email, signUpRequest.password))
    }


    fun login(loginRequest: LoginRequest): LoginResponse {

        val user: User? = userRepository.findByEmail(loginRequest.email)

        if (bCryptPasswordEncoder.matches(loginRequest.password, user?.password)) {
            println("Correct password ${loginRequest.password}")
        } else {
            println("Incorrect password ${loginRequest.password}")
        }

        val token = authenticationService.authenticate(loginRequest.email, loginRequest.password)

        return LoginResponse(token)
    }
}

class CredentialsInUseException(message: String?): Exception(message)
