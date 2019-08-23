package fi.delta.delta.services

import org.springframework.stereotype.Service

@Service
class MainService {
    data class MainResponse(val message: String)

    fun getValue() = MainResponse("Hello service!")
}