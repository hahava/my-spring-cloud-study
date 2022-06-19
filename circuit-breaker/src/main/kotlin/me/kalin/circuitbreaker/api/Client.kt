package me.kalin.circuitbreaker.api

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class Client {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun fetchApi(errorTrigger: Boolean) {
        if (errorTrigger) {
            logger.error("error occurred")
            throw RuntimeException("")
        }
        logger.info("no error occurred")
    }
}