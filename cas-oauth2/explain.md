## 需求

1. client通过调用cas服务进行身份认证，获得access-token
2. restful服务中使用spring-security-oauth2进行身份认证
3. client带access-token访问restful服务
4. 多restful服务中相互调用

## client通过调用cas服务进行身份认证
    
    cas服务用于单点登录、授权，其中可使用oauth2、openId等协议进行身份认证，以下主要讲cas使用oauth2进行认证

### cas服务器中使用oauth2协议
    
1. 正常搭建cas服务

    [学习地址](https://chenadminchen.github.io/2018/02/28/cas-server-set-up/ 'url')

2. cas服务注册oauth2的服务

2.1. 添加依赖

```
compile "org.apereo.cas:cas-server-support-oauth-webflow:${project.casVersion}"
```

2.2. 注册oauth2有服务
添加新服务，内容如下,[学习地址](https://apereo.github.io/cas/5.3.x/installation/OAuth-OpenId-Authentication.html)
generateRefreshToken:true,其access-token值的返回中带上了refresh-token
```
{
  "@class" : "org.apereo.cas.support.oauth.services.OAuthRegisteredService",
  "clientId": "clientid",
  "clientSecret": "clientSecret",
  "serviceId" : "^(https|http|imaps)://.*",
  "name" : "OAuthService",
  "id" : 100,
  "generateRefreshToken":true, 
  "jsonFormat" : true,
  "supportedGrantTypes":{"java.util.sets",["password"]}
}
```

2.3. 测试

> oauth2客户端的授权模式

[学习地址](http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html)  
客户端必须得到用户的授权（authorization grant），才能获得令牌（access token）。OAuth 2.0定义了四种授权方式。
1. 授权码模式（authorization code）
1. 简化模式（implicit）
1. 密码模式（resource owner password credentials）
1. 客户端模式（client credentials）

2.3.1.1. 授权码模式（authorization_code）

授权码模式的步骤如下：

（A）用户访问客户端，后者将前者导向认证服务器。

（B）用户选择是否给予客户端授权。

（C）假设用户给予授权，认证服务器将用户导向客户端事先指定的"重定向URI"（redirection URI），同时附上一个授权码。

（D）客户端收到授权码，附上早先的"重定向URI"，向认证服务器申请令牌。这一步是在客户端的后台的服务器上完成的，对用户不可见。

（E）认证服务器核对了授权码和重定向URI，确认无误后，向客户端发送访问令牌（access token）和更新令牌（refresh token）。

下面是上面这些步骤所需要的参数。

A步骤中，客户端申请认证的URI，包含以下参数：

response_type：表示授权类型，必选项，此处的值固定为"code"  
client_id：表示客户端的ID，必选项  
redirect_uri：表示重定向URI，可选项  
scope：表示申请的权限范围，可选项  
state：表示客户端的当前状态，可以指定任意值，认证服务器会原封不动地返回这个值。<br/>

2.3.1.2. 授权码模式（authorization_code）测试

*将cas中用户的权限给予百度<br/>*
*client_id、client_secret的值与cas服务器中的设置相同*  
*grant_type=authorization_code*

>访问如下地址：  
&emsp;&emsp;-> 跳转到登录页面 -> 授权 -> 返回授权码 

```
https://127.0.0.1:8443/cas/oauth2.0/authorize?response_type=code&client_id=clientid&redirect_uri=http://www.baidu.com
```

>使用授权码登录，授权码只有很短的时间
```
https://127.0.0.1:8443/cas/oauth2.0/accessToken?grant_type=authorization_code&client_id=clientid&client_secret=clientSecret&code=OC-1-jz1oIxIWk3k0oYHS8HmQ9Ma2JCPzq0Dt&redirect_uri=http://www.baidu.com
```

>restul access-token /refresh-token

```json
{
"access_token": "AT-2-xKYAmuompAONGHrfBMcJSM2BZ844Lws-",
"token_type": "bearer",
"expires_in": 28800,
"refresh_token": "RT-2-mL-vnsVO0ZMUZieemM2aOjBnEeU-BK5z"
}
```

>访问restful服务时带上access-token 
    -k 跳过ssl认证
```
curl -v http://127.0.0.1:9091/get-a -H "Authorization: Bearer AT-2-xKYAmuompAONGHrfBMcJSM2BZ844Lws-" -k
```

>获得用户信息

```
curl -v https://127.0.0.1:8443/cas/oauth2.0/profile -H "Authorization: Bearer AT-4-5yIDnep-FYdHfTzVBtEp8Y87TOv1OBA-" -k
```

>result 

```json
{
    "attributes": {
        "gender": 1,
        "id": 2,
        "name": "管理员"
    },
    "id": "123@163.com"
}
```
2.3.2.1 简化模式（implicit）

简化模式（implicit grant type）不通过第三方应用程序的服务器，直接在浏览器中向认证服务器申请令牌，跳过了"授权码"这个步骤，因此得名。所有步骤在浏览器中完成，令牌对访问者是可见的，且客户端不需要认证。

它的步骤如下：

（A）客户端将用户导向认证服务器。

（B）用户决定是否给于客户端授权。

（C）假设用户给予授权，认证服务器将用户导向客户端指定的"重定向URI"，并在URI的Hash部分包含了访问令牌。

（D）浏览器向资源服务器发出请求，其中不包括上一步收到的Hash值。

（E）资源服务器返回一个网页，其中包含的代码可以获取Hash值中的令牌。

（F）浏览器执行上一步获得的脚本，提取出令牌。

（G）浏览器将令牌发给客户端。

下面是上面这些步骤所需要的参数。

A步骤中，客户端发出的HTTP请求，包含以下参数：

response_type：表示授权类型，此处的值固定为"token"，必选项。
client_id：表示客户端的ID，必选项。
redirect_uri：表示重定向的URI，可选项。
scope：表示权限范围，可选项。
state：表示客户端的当前状态，可以指定任意值，认证服务器会原封不动地返回这个值。

2.3.2.2. 测试

>访问如下地址：  
&emsp;&emsp;-> 跳转到登录页面 -> 授权 -> 重定向到百度，url带access-token

```
https://127.0.0.1:8443/cas/oauth2.0/authorize?response_type=token&client_id=clientid&redirect_uri=http://www.baidu.com
```

>result
```
https://www.baidu.com/#access_token=AT-10-hQ5Moemz2jGZxwFnB7OQ3Jog-2ugFy8K&token_type=bearer&expires_in=28800&refresh_token=RT-10-vHLvWaz-GXtW7EiNHgbEL2dXyW2n5woW
```

2.3.3.1.密码模式(Resource Owner Password Credentials Grant)

用户向客户端提供自己的用户名和密码。客户端使用这些信息，向"服务商提供商"索要授权。
在这种模式中，用户必须把自己的密码给客户端，但是客户端不得储存密码。这通常用在用户对客户端高度信任的情况下，比如客户端是操作系统的一部分，或者由一个著名公司出品。而认证服务器只有在其他授权模式无法执行的情况下，才能考虑使用这种模式。 

步骤如下：

（A）用户向客户端提供用户名和密码。

（B）客户端将用户名和密码发给认证服务器，向后者请求令牌。

（C）认证服务器确认无误后，向客户端提供访问令牌。

B步骤中，客户端发出的HTTP请求，包含以下参数：

grant_type：表示授权类型，此处的值固定为"password"，必选项  
username：表示用户名，必选项  
password：表示用户的密码，必选项  
scope：表示权限范围，可选项

2.3.3.2. 测试

```
#在postman中不需要设置 
    Basic auth : username=clientid / password=clientSecret,
 否则获得access-token值后，无法根据/cas/oauth2.0/profile 获取到用户信息

curl -v https://127.0.0.1:8443/cas/oauth2.0/accessToken?grant_type=password&client_id=clientid&username=123@163.com&password=7777 -H "Content-Type:application/x-www-form-urlencoded"
```

>result

```json
{
    "access_token": "AT-11-o5CegNMTM5M6i5ddgeSy2IVef0bAnzGp",
    "token_type": "bearer",
    "expires_in": 28800,
    "refresh_token": "RT-11-7uHkFKCsWz-xyedUPwH1-E-Em6DJOYE1"
}
```

>refresh-token

```
#在postman中设置
    Bearer Token: "AT-1-U5M2uIQpQ7IHtC-7EbuD8w--xPdXcUZl"(access-token)
curl -v https://127.0.0.1:8443/cas/oauth2.0/accessToken -H "Content-Type:application/x-www-form-urlencoded"
```

> refresh-result

```
{
    "access_token": "AT-3-glqmeidheE10MQI8951M3tbBLY0-5grQ",
    "token_type": "bearer",
    "expires_in": 28800
}
```
2.3.4.1. 客户端模式（Client Credentials Grant）

指客户端以自己的名义，而不是以用户的名义，向"服务提供商"进行认证。严格地说，客户端模式并不属于OAuth框架所要解决的问题。
在这种模式中，用户直接向客户端注册，客户端以自己的名义要求"服务提供商"提供服务，其实不存在授权问题。

它的步骤如下：

（A）客户端向认证服务器进行身份认证，并要求一个访问令牌。

（B）认证服务器确认无误后，向客户端提供访问令牌。

A步骤中，客户端发出的HTTP请求，包含以下参数：

grant_type：表示授权类型，此处的值固定为"client_credentials"，必选项。
scope：表示权限范围，可选项。

2.3.4.2. 测试


```
curl https://127.0.0.1:8443/cas/oauth2.0/accessToken?grant_type=client_credentials&client_id=clientid&client_secret=clientSecret -H "Content-Type:application/x-www-form-urlencoded"
```

> result

```
# 获得的为临时值，在cas服务器中无法生成正确的用户信息，因为在认证的过程中没有提供用户信息
{
    "access_token": "AT-3-iR5oKWWUo2PBQ5pR7iEvAPPtTTzA-i6b",
    "token_type": "bearer",
    "expires_in": 28800,
    "refresh_token": "RT-3-E1qGvCmwYhsFwPBtjtZltGfQljyQ71t9"
}
```

## restful服务中使用spring-security-oauth2进行身份认证

>添加依赖

```
  compile('org.springframework.cloud:spring-cloud-starter-security')
  compile('org.springframework.cloud:spring-cloud-starter-oauth2:2.0.0.RELEASE')
```
 
>添加配置文件

```
security:
  ignored: /
  sessions: never   # session策略
  oauth2:
    resource:
      userInfoUri: https://127.0.0.1:8443/cas/oauth2.0/profile
      preferTokenInfo: false
```

>spring boot项目内添加配置文件

在启动类中添加@EnableAuthorizationServer注解
    
### client带access-token访问restful服务
    
```
curl http://127.0.0.1:9091/get-a -H "Authorization:Bearer AT-3-glqmeidheE10MQI8951M3tbBLY0-5grQ"
```

### client通过spring cloud zuul访问spring boot的restful服务

> 在spring cloud zuul项目内添加配置文件

```
zuul:

  routes:
    api-a:
      #      path: /api-a/**
#      sensitiveHeaders:   设置为空时，可将access-token值转发过去(1)
      url: http://127.0.0.1:9091
#      customSensitiveHeaders: true  设置为true时，可将access-token值转发过去(2)  // (1) (2)选择一项
    api-b:
      url: http://127.0.0.1:9092
#      sensitiveHeaders:
#      customSensitiveHeaders: true
  ignore-security-headers: false  false，可将access-token值转发过去
``` 
