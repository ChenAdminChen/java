package com.chen.swagger.config

import com.google.common.collect.Lists.newArrayList
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.schema.ModelRef
import springfox.documentation.swagger.web.SecurityConfiguration
import java.util.*
import springfox.documentation.swagger.web.SecurityConfigurationBuilder
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.builders.OAuthBuilder
import springfox.documentation.builders.AuthorizationCodeGrantBuilder
import springfox.documentation.service.*


/**
 * swagger configuration class
 * url:http://localhost:8080/swagger-ui.html
 *
 * resources: [
 * https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api,
 * https://github.com/springfox/springfox-oath2-demo/blob/master/src/main/java/myprojects/sample/oauth/config/SwaggerConfig.java
 * ]
 *
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // appoint scan request handler selector in all package
                .apis(RequestHandlerSelectors.any())

                .paths(PathSelectors.any())
                .build()

                .useDefaultResponseMessages(false)
                .globalResponseMessage(
                        RequestMethod.GET, newArrayList(
                        ResponseMessageBuilder()
                                .code(500)
                                .message("500 message")
                                .responseModel(ModelRef("Error"))
                                .build(),
                        ResponseMessageBuilder()
                                .code(403)
                                .message("Forbidden!")
                                .build(),
                        ResponseMessageBuilder()
                                .code(200)
                                .message("get/put/delete ok")
                                .build(),
                        ResponseMessageBuilder()
                                .code(201)
                                .message("post ok")
                                .build(),
                        ResponseMessageBuilder()
                                .code(404)
                                .message("not found resources")
                                .build()
                ))

                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()))

    }

    fun apiInfo(): ApiInfo {
        return ApiInfoBuilder().title("api 接口")
                .description("rest服务说明")
                .version("2.0").contact(Contact("liu", "url", "123qq@.com")).build()
    }

    fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

    //define a SecurityConfiguration bean in our Swagger configuration – and set some defaults:
    @Bean
    fun security(): SecurityConfiguration {
        return SecurityConfigurationBuilder.builder()
                .clientId("client")
                .clientSecret("123456")
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build()
    }

    private fun securityContext(): SecurityContext {
        return SecurityContext.builder()
                .securityReferences(
                        Arrays.asList(SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.regex("/.*"))
                .build()
    }

    //define an OAuth scheme used to secure our Resource Server
    private fun securityScheme(): SecurityScheme {
        val grantType = AuthorizationCodeGrantBuilder()
                .tokenEndpoint(TokenEndpoint("http://localhost:9876" + "/oauth/token", "oauthtoken"))
                .tokenRequestEndpoint(
                        TokenRequestEndpoint("http://localhost:9876" + "/oauth/authorize", "clientId", "test"))
                .build()

        return OAuthBuilder().name("spring_oauth")
                .grantTypes(Arrays.asList(grantType) as List<GrantType>)
                .scopes(Arrays.asList(scopes()) as List<AuthorizationScope>)
                .build()
    }

    private fun scopes(): Array<AuthorizationScope> {
        return arrayOf(AuthorizationScope("read", "for read operations"), AuthorizationScope("write", "for write operations"), AuthorizationScope("foo", "Access foo API"))
    }

}
