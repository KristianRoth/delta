package fi.delta.delta


import fi.delta.delta.models.User
import fi.delta.delta.repository.UserRepository
import fi.delta.delta.services.UserService
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.JUnitSoftAssertions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
class DeltaApplicationTests {

	@Autowired
	lateinit var userService: UserService

	@Autowired
	lateinit var userRepository: UserRepository

	@get:Rule
	var softly = JUnitSoftAssertions()

	@Test
	fun contextLoads() {
	}

	@Test
	@Transactional
	fun `'retrieveUser' should retrieve existing user`() {
		userRepository.save(User("retrieveUser@example.org", "retrieveUser", "test"))


		val result = userService.getUserByEmail("retrieveUser@example.org")
		assertThat(result).isNotNull

		val resultById = userService.getUserById(result?.id!!)

	}

}
