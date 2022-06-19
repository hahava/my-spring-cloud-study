package me.kalin.circuitbreaker.service

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import me.kalin.circuitbreaker.api.Client
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class HomeService(
    private val client: Client
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @CircuitBreaker(name = "APIServer", fallbackMethod = "errorOccurred")
    fun circuitBreakerTest(errorTrigger: Boolean) = client.fetchApi(errorTrigger)

    private fun errorOccurred(throwable: Throwable) {
        logger.error("fuck...")
    }
}