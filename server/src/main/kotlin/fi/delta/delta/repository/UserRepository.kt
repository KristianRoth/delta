package fi.delta.delta.repository

import fi.delta.delta.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Int> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): User?
    fun existsByUsername(username: String): Boolean
}
