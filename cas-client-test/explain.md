## 项目

使用 cas jwt认证用户身份


> 获得 TGT

```

curl https://127.0.0.1:8443/cas/v1/tickets?service=https://localhost:8080/login/cas  -H "Content-Type:application/x-www-form-urlencoded" -X POST -d "username=00000010@yf.com&password=yf8541277&additionalParam1=paramvalue"

get Location: https://cas.example.org:8443/cas/v1/tickets/TGT-1-tPVwdynj6fTg--TQASncWNebJ-Zin68fqixPgYV7qjWYGrrzgoSybVeIwjsX1m4jZvochen-T4

<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">
<html>
    <head>
        <title>201 Created</title>
    </head>
    <body>
        <h1>TGT Created</h1>
        <form action="https://cas.example.org:8443/cas/v1/tickets/TGT-1-tPVwdynj6fTg--TQASncWNebJ-Zin68fqixPgYV7qjWYGrrzgoSybVeIwjsX1m4jZvochen-T4" method="POST">Service:
            <input type="text" name="service" value="">
            <br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>

```
  
> 获得 jwt token

```

curl -X POST https://cas.example.org:8443/cas/v1/tickets/TGT-1-tPVwdynj6fTg--TQASncWNebJ-Zin68fqixPgYV7qjWYGrrzgoSybVeIwjsX1m4jZvochen-T4?service=https://localhost:8080/login/cas

get: JWT 
若过期则提示：
<cas:serviceResponse xmlns:cas='http://www.yale.edu/tp/cas'>
    <cas:proxyFailure code="INVALID_TICKET">未能够识别出目标 &#39;PGTIOU-1-vaeR27-vAUI-0l4VGbT5NhkyByc5CPsQuO5sSjlPxP47nvrzapQNMjlIsd3WZfFjttUchen-T4&#39;票根</cas:proxyFailure>
</cas:serviceResponse>

```

> 使用jwt访问

```

curl -X GET -v http://localhost:8082/security -H "Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.ZXlKNmFYQWlPaUpFUlVZaUxDSmhiR2NpT2lKa2FYSWlMQ0psYm1NaU9pSkJNVEk0UTBKRExVaFRNalUySW4wLi42NVA0UnAzTEQzdHptTkt3U0dJMXp3LmdUQVpqM1pnakdpZk9GVkRlcVJubDJ5ay1IaGFtQkt6bDRNTXg0b01vMUE4MGpYaVNBSzJNT2NXaksxdjNvTUYta21JMFdfYVVVYjNOT2xINEJrdTBBV2FCOFk4d05XU2E3dklZX2NIb3UxR3V5SGJkUWZGLVA5WUFsMHdqUVlQWDJVQzBSOEIzZ3ZwbksyWmNJajdWZVhSTFZPd2R2OVBKaFBSSFBTZVcxYmI2WEV5V2hwM3UzVGQzYTlQMTlNN0trNkxkb0gxMlhfSFJ0bU1zbjBTWGhCTmxobEhxWlZCcnliNGJaTmNsYnFKQTJ1dllsejJjOW9UOG9KRWVMaWdEcURMU0FCLVBubzRCZnJVVy04S0I2aEctdVVtNVdpbWFVeDVzQjM5SE01bzVKYzBXV0ZNMVBBM3VPMTF0NDM0Q1MwdnJ5aV9BN3ZVODQ5b3BxakZpMGh3WWJaUUhmdVRfNUgwTThBTzlSc0ZqY2dkX21KcFhQZzc0cWg2MTNvTXpEQkd3OXVVeXVGS29qaWpVcTEwN0NlQkVlQTV4NFVmbXg3aTJPZGpPU0Uud0pIWEVxUGFhNThuOE5mSW9hX1dRdw==.--D2WvbriGVVGwjWSqxmSUkGZ1r50ueCDkSECuaMkFjuG0LNPGNd9gSVcDjcB11sD0byVwWJ5axzphPR1YLb6Q"


```
