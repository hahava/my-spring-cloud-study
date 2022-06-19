package me.kalin.circuitbreaker.config

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration
import javax.sql.DataSource

@Configuration
class BeanConfig {
    @Bean
    fun circuitBreaker(): CircuitBreakerConfigCustomizer =
        CircuitBreakerConfigCustomizer.of("APIServer") {
            it.failureRateThreshold(50F)
            it.slowCallRateThreshold(100F)
            it.slowCallDurationThreshold(Duration.ofMillis(60000))
            it.slidingWindowSize(100)
            it.permittedNumberOfCallsInHalfOpenState(10)
            it.maxWaitDurationInHalfOpenState(Duration.ofMillis(10))
            it.slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            it.slidingWindowSize(100)
            it.minimumNumberOfCalls(100)
            it.waitDurationInOpenState(Duration.ofMillis(6000))
            it.automaticTransitionFromOpenToHalfOpenEnabled(false)
        }

    @Bean
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
            .type()
            .build()
    }
}