package com.test.client1.casoauthclient1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails


@SpringBootApplication
class CasOauthClient1Application {

//    private fun disableSslVerification() {
//        try {
//            // Create a trust manager that does not validate certificate chains
//            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
//                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate>? {
//                    return null
//                }
//
//                override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
//                override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
//            })
//
//            // Install the all-trusting trust manager
//            val sc = SSLContext.getInstance("SSL")
//            sc.init(null, trustAllCerts, java.security.SecureRandom())
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
//
//            // Create all-trusting host name verifier
//            val allHostsValid = HostnameVerifier { hostname, session -> true }
//
//            // Install the all-trusting host verifier
//            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid)
//        } catch (e: NoSuchAlgorithmException) {
//            e.printStackTrace()
//        } catch (e: KeyManagementException) {
//            e.printStackTrace()
//        }
//
//    }


    @Bean
    fun oauth2RestTemplate(oAuth2ClientContext: OAuth2ClientContext, details: OAuth2ProtectedResourceDetails): OAuth2RestTemplate {
        return OAuth2RestTemplate(details,oAuth2ClientContext)
    }

}


//@Component
//@EnableOAuth2Sso // 实现基于OAuth2的单点登录，建议跟踪进代码阅读以下该注解的注释，很有用
//class SecurityConfiguration : WebSecurityConfigurerAdapter() {
//    @Throws(Exception::class)
//    public override fun configure(http: HttpSecurity) {
//        http.antMatcher("/**")
//                // 所有请求都得经过认证和授权
//                .authorizeRequests().anyRequest().authenticated()
//                .and().authorizeRequests().antMatchers("/", "/anon").permitAll()
//                .and()
//                // 这里之所以要禁用csrf，是为了方便。
//                // 否则，退出链接必须要发送一个post请求，请求还得带csrf token
//                // 那样我还得写一个界面，发送post请求
//                .csrf().disable()
//                // 退出的URL是/logout
//                .logout().logoutUrl("/logout").permitAll()
//                // 退出成功后，跳转到/路径。
//                .logoutSuccessUrl("/login")
//    }
//}


fun main(args: Array<String>) {
    runApplication<CasOauthClient1Application>(*args)
}
