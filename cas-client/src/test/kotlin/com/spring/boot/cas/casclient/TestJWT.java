package com.spring.boot.cas.casclient;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.keys.AesKey;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.Key;

/**
 * use TestJWT jwt() decode cas-jwt
 */
public class TestJWT {
    @Test
    public void jwt() throws Exception {

        //signingKey:  为service.json中的jwtAsServiceTicketSigningKey
        //encryptionKey: 为service.json中的jwtAsServiceTicketEncryptionKey
        final String signingKey = "ONOqVlFFievk5lo2Yz84S8J41DTrAJDbt4MnB2ZpniKAlzHitlL12xJ1VYxB8GpB";
        final String encryptionKey = "afkeykeykeykyekyefdafsadfsdafdafdafsadfsafa";

        final Key key = new AesKey(signingKey.getBytes(StandardCharsets.UTF_8));

        final JsonWebSignature jws = new JsonWebSignature();

        //为cas jwt
        jws.setCompactSerialization("eyJhbGciOiJIUzUxMiJ9.ZXlKNmFYQWlPaUpFUlVZaUxDSmhiR2NpT2lKa2FYSWlMQ0psYm1NaU9pSkJNVEk0UTBKRExVaFRNalUySW4wLi42NVA0UnAzTEQzdHptTkt3U0dJMXp3LmdUQVpqM1pnakdpZk9GVkRlcVJubDJ5ay1IaGFtQkt6bDRNTXg0b01vMUE4MGpYaVNBSzJNT2NXaksxdjNvTUYta21JMFdfYVVVYjNOT2xINEJrdTBBV2FCOFk4d05XU2E3dklZX2NIb3UxR3V5SGJkUWZGLVA5WUFsMHdqUVlQWDJVQzBSOEIzZ3ZwbksyWmNJajdWZVhSTFZPd2R2OVBKaFBSSFBTZVcxYmI2WEV5V2hwM3UzVGQzYTlQMTlNN0trNkxkb0gxMlhfSFJ0bU1zbjBTWGhCTmxobEhxWlZCcnliNGJaTmNsYnFKQTJ1dllsejJjOW9UOG9KRWVMaWdEcURMU0FCLVBubzRCZnJVVy04S0I2aEctdVVtNVdpbWFVeDVzQjM5SE01bzVKYzBXV0ZNMVBBM3VPMTF0NDM0Q1MwdnJ5aV9BN3ZVODQ5b3BxakZpMGh3WWJaUUhmdVRfNUgwTThBTzlSc0ZqY2dkX21KcFhQZzc0cWg2MTNvTXpEQkd3OXVVeXVGS29qaWpVcTEwN0NlQkVlQTV4NFVmbXg3aTJPZGpPU0Uud0pIWEVxUGFhNThuOE5mSW9hX1dRdw==.--D2WvbriGVVGwjWSqxmSUkGZ1r50ueCDkSECuaMkFjuG0LNPGNd9gSVcDjcB11sD0byVwWJ5axzphPR1YLb6Q");

        jws.setKey(key);
        if (!jws.verifySignature()) {
            throw new Exception("JWT verification failed");
        }

        final byte[] decodedBytes = Base64.decodeBase64(jws.getEncodedPayload().getBytes(StandardCharsets.UTF_8));
        final String decodedPayload = new String(decodedBytes, StandardCharsets.UTF_8);

        final JsonWebEncryption jwe = new JsonWebEncryption();
        final JsonWebKey jsonWebKey = JsonWebKey.Factory
                .newJwk("\n" + "{\"kty\":\"oct\",\n" + " \"k\":\"" + encryptionKey + "\"\n" + "}");

        jwe.setCompactSerialization(decodedPayload);
        jwe.setKey(new AesKey(jsonWebKey.getKey().getEncoded()));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(jwe.getPlaintextString());

        json.get("sub");

        System.out.println(jwe.getPlaintextString());
    }
}
