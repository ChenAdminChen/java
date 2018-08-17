##  该项目说明

该项目主要用于rest service proxy，用于获得PT,主要功能全在SecurityConfig内,代理服务地址必需为https，可查看cas portocol中的Proxy web flow diagram图

###

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
  
> 获得 ST

```

curl -X POST https://cas.example.org:8443/cas/v1/tickets/TGT-1-tPVwdynj6fTg--TQASncWNebJ-Zin68fqixPgYV7qjWYGrrzgoSybVeIwjsX1m4jZvochen-T4?service=https://localhost:8080/login/cas

get: ST （过期时间为10秒）

若过期则提示：
<cas:serviceResponse xmlns:cas='http://www.yale.edu/tp/cas'>
    <cas:proxyFailure code="INVALID_TICKET">未能够识别出目标 &#39;PGTIOU-1-vaeR27-vAUI-0l4VGbT5NhkyByc5CPsQuO5sSjlPxP47nvrzapQNMjlIsd3WZfFjttUchen-T4&#39;票根</cas:proxyFailure>
</cas:serviceResponse>

```

> 获得 PGT

```
curl -X GET https://127.0.0.1:8080/security?ticket=ST-3-aKagvnTw0NRdlDj1WrKMX7-GZeochen-T4&pgtUrl=https://localhost:8080/login/cas/proxyreceptor&service=https:8080/login/cas

get: <cas:serviceResponse xmlns:cas='http://www.yale.edu/tp/cas'>
         <cas:authenticationSuccess>
             <cas:user>00000010@yf.com</cas:user>
             <cas:proxyGrantingTicket>PGTIOU-1-vaeR27-vAUI-0l4VGbT5NhkyByc5CPsQuO5sSjlPxP47nvrzapQNMjlIsd3WZfFjttUchen-T4</cas:proxyGrantingTicket>
         </cas:authenticationSuccess>
     </cas:serviceResponse>
```

> 获得 PT

```

curl -X GET https://127.0.0.1:8443/cas/proxy?pgt=PGT-9-ApyjufwTuRaii0vfaWvTZ1AKPTXF4LMy2rQsferblO2bGf0X6VUUuo05V2kULF6Ejbwchen-T4&service=https:8080/login/cas

get: PT （过期时间为10秒）
```

> 使用PT访问

```

curl -X GET https://127.0.0.1:8080/security?ticket=PT-28-DMpv0hTJCtD3TtORv164kXQ8Yhkchen-T4

```

#### 可将cas.postman_collection.json导入到postman，发送请求