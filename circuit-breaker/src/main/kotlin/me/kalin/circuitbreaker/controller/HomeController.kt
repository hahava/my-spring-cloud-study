package me.kalin.circuitbreaker.controller

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import me.kalin.circuitbreaker.service.HomeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(
    private val homeService: HomeService
) {
    @GetMapping("/circuit-breaker/{errorTrigger}")
    fun testCircuitBreaker(@PathVariable errorTrigger: Boolean): Map<String, Any> {
        homeService.circuitBreakerTest(errorTrigger)
        return mapOf(
            "metrics" to CircuitBreaker.ofDefaults("APIServer").metrics,
            "status" to CircuitBreaker.ofDefaults("APIServer").state
        )
    }

}