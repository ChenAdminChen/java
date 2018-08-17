package com.chen.spring.casclienttest.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CasProperties {
    @Value("\${cas.server.host.url}")
    val casServerUrl: String? = null

    @Value("\${cas.server.host.login_url}")
    val casServerLoginUrl: String? = null

    @Value("\${cas.server.host.logout_url}")
    val casServerLogoutUrl: String? = null

    @Value("\${app.server.host.url}")
    val appServerUrl: String? = null

    @Value("\${app.login.url}")
    val appLoginUrl: String? = null

    @Value("\${app.logout.url}")
    val appLogoutUrl: String? = null
}