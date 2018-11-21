package com.cas.oauth2.casoauth2client1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer


@SpringBootApplication
@EnableResourceServer
@EnableFeignClients
@EnableDiscoveryClient
class CasOauth2Client1Application{

    //开启此配置时需要
    @Bean
    fun oauth2RestTemplate(oAuth2ClientContext: OAuth2ClientContext, details: OAuth2ProtectedResourceDetails): OAuth2RestTemplate {
        return OAuth2RestTemplate(details,oAuth2ClientContext)
    }

//    @Bean
//    fun requestTokenBearerInterceptor(): RequestInterceptor {
//        return RequestInterceptor { requestTemplate ->
//            val details = SecurityContextHolder.getContext().authentication.details as OAuth2AuthenticationDetails
//
//            requestTemplate.header("Authorization", "bearer " + details.tokenValue)
//        }
//    }

//    @Bean
//    fun reddit(): OAuth2ProtectedResourceDetails {
//        val details = AuthorizationCodeResourceDetails()
//        details.id = "reddit"
//        details.clientId = "clientid"
//        details.clientSecret = "clientSecret"
//        details.tokenName = "oauth_token"
//        details.scope = Arrays.asList("all")
//        details.preEstablishedRedirectUri = "http://localhost/login"
//        details.isUseCurrentUri = true
//        return details
//    }

//    @Bean
//    fun redditRestTemplate(clientContext: OAuth2ClientContext): OAuth2RestTemplate {
//        val template = OAuth2RestTemplate(reddit(), clientContext)
//        val accessTokenProvider = AccessTokenProviderChain(
//                Arrays.asList(
//                        MyAuthorizationCodeAccessTokenProvider(),
//                        ImplicitAccessTokenProvider(),
//                        ResourceOwnerPasswordAccessTokenProvider(),
//                        ClientCredentialsAccessTokenProvider())
//        )
//        template.setAccessTokenProvider(accessTokenProvider)
//        return template
//    }
}

fun main(args: Array<String>) {
    runApplication<CasOauth2Client1Application>(*args)
}
