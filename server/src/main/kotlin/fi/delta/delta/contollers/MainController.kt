package fi.delta.delta.contollers

import fi.delta.delta.models.User
import fi.delta.delta.services.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class MainController @Autowired constructor(
        private val mainService: MainService,
        private val userService: UserService
) {

    @GetMapping("/hello", produces = ["application/json"])
    fun hello() = mainService.getValue()


    @GetMapping("/getUser", produces = ["application/json"])
    fun getUser(
            @RequestParam(value = "id", required = false) userId: Int?,
            @RequestParam(value="email", required = false) userEmail: String?
            ): ResponseEntity<User> {

        userId?.let {id ->
            userService.getUserById(id)?.let {
                return ResponseEntity.ok(it)
            }
        }
        userEmail?.let { email ->
            userService.getUserByEmail(email)?.let {
                return ResponseEntity.ok(it)
            }
        }
        return ResponseEntity.notFound().build()
    }

    @PostMapping("/login", produces = ["application/json"])
    fun login (@RequestBody() loginRequest: LoginRequest ): ResponseEntity<LoginResponse> {
        return ResponseEntity.ok(userService.login(loginRequest))
    }

    @PostMapping("/signUp")
    fun login(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<LoginResponse> {
        return try {
            ResponseEntity.ok(userService.signUp(signUpRequest))
        }
        catch (e: CredentialsInUseException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }
}