package com.chen.spring.casclienttest.config

import org.apache.commons.codec.binary.Base64
import org.codehaus.jackson.map.ObjectMapper
import org.jose4j.jwe.JsonWebEncryption
import org.jose4j.jwk.JsonWebKey
import org.jose4j.jws.JsonWebSignature
import org.jose4j.keys.AesKey
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 用于认证token值是否有效
 * 其访问路径：curl http://localhost:8082/security -H "Authorization:Bearer cdafdafsalkfeorieqor" -v
 */
//@Component
class JWTAuthenticationFilter(authenticationManager: AuthenticationManager?) : BasicAuthenticationFilter(authenticationManager) {

//    fun JWTAuthenticationFilter(authenticationManager: AuthenticationManager){
//        super(authenticationManager)
//    }

    @Throws(IOException::class, ServletException::class)
    protected override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader("Authorization")

        if (header == null || !header!!.startsWith("Bearer ")) {
            chain.doFilter(request, response)
            return
        }

        val authentication = getAuthentication(request)

        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader("Authorization")
        if (token != null) {


            // parse the token.
//            val user = Jwts.parser()
//                    .setSigningKey("ONOqVlFFievk5lo2Yz84S8J41DTrAJDbt4MnB2ZpniKAlzHitlL12xJ1VYxB8GpB")
//                    .parseClaimsJws(token!!.replace("Bearer ", ""))
//                    .body
//                    .subject

            //以下的解码方法按cas 中的 jwt解码要求
            val signingKey = "ONOqVlFFievk5lo2Yz84S8J41DTrAJDbt4MnB2ZpniKAlzHitlL12xJ1VYxB8GpB"
            val encryptionKey = "afkeykeykeykyekyefdafsadfsdafdafdafsadfsafa"

            val key = AesKey(signingKey.toByteArray(StandardCharsets.UTF_8))

            val jws = JsonWebSignature()

            jws.compactSerialization = token!!.replace("Bearer ", "")

            jws.setKey(key)
            if (!jws.verifySignature()) {
                throw Exception("JWT verification failed")
            }


            val decodedBytes = Base64.decodeBase64(jws.encodedPayload.toByteArray(StandardCharsets.UTF_8))

            val decodedPayload = String(decodedBytes, StandardCharsets.UTF_8)

            val jwe = JsonWebEncryption()
            val jsonWebKey = JsonWebKey.Factory
                    .newJwk("\n{\"kty\":\"oct\",\n \"k\":\"$encryptionKey\"\n}")

            jwe.compactSerialization = decodedPayload
            jwe.key = AesKey(jsonWebKey.key.encoded)

            val objectMapper = ObjectMapper()
            val json = objectMapper.readTree(jwe.plaintextString)

            val user =json.get("sub").toString()

            System.out.println(jwe.plaintextString)

            return if (user != null) {
                val authorities = HashSet<AuthorityInfo>()
                val authorityInfo = AuthorityInfo("TEST")
                authorities.add(authorityInfo)

                UsernamePasswordAuthenticationToken(user, null,authorities)

            } else null
        }
        return null
    }

}