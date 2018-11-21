# 项目介绍

## cas-oauth2-client1

介绍：  
1.使用cas服务作为用户身份认证服务
2.cas服务器中使用了oauth2协议
3.使用oauth2的password模式进行身份检查
3.为spring-boot项目，并将其注册到eureka-server中
4.提供rest服务接口
5.调用其他spring-boot项目中的rest服务接口,并通过身份认证

## cas-oauth2-client2

介绍：  
1.使用cas服务作为用户身份认证服务
2.cas服务器中使用了oauth2协议
3.使用oauth2的password模式进行身份检查
3.为spring-boot项目，并将其注册到eureka-server中
4.提供rest服务接口

## cas-oauth2-eureka-server
介绍：  
1.eureka-server注册中心

## cas-oauth2-server
介绍：  
1.自定义oauth2的server认证中心（未实现）

## cas-oauth2-zuul
介绍：  
1.服务网关
2.注册到eureka-server中
3.使用该项目作为所有rest服务的入口

## casoauth2permission
介绍：  
1.自定义权限注解

## permission-test
介绍：     
1.使用自定义的权限注解进行注解，生成权限表
