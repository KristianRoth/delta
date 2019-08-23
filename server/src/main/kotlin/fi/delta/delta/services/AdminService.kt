package fi.delta.delta.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


data class PingResponse(
    val message: String = "pong"
)


@Service
class AdminService @Autowired constructor (
)