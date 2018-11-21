package com.spring.boot.cas.casclient

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.jose4j.keys.AesKey
import org.jose4j.jwk.JsonWebKey
import org.jose4j.jwe.JsonWebEncryption
import org.jose4j.jws.JsonWebSignature
import java.nio.charset.StandardCharsets
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
class CasClientApplicationTests {

    @Test
    fun contextLoads() {

    }

}
