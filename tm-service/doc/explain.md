# transaction manager center

## 作用

&nbsp;&nbsp;&nbsp;&nbsp;TxManager作为分布式事务的控制方，管理事物組的创建及销毁

## redis在事务控制中心的作用

1. 事务控制中心注册到redis服务器中，通过redis服务的发布与订阅功能广播事务控制中心的运行相关消息
2. 事务控制中心将事物組相关的的消息存入在redis缓存

区分事务控制中心是否属于同一个分布式事务中心主要看TxManager下的连接的db资源(mysql 、redis)

## 事务中心的搭建

以下的实现方式基于lcn的5.0.2.RELEASE版本

### redis服务器的搭建

以下基于linux下实现
```
#更新
sudo apt-get update

#安装
sudo apt-get install redis-server

#启动
redis-server

#修改配置文件，便于其他PC端连接redis服务器
1. sudo nano /etc/redis/redis.conf

## 注释掉下面的配置
2. #bind 127.0.0.1

## 将redis的保护模式取消

3. protected-mode no

## 重启
4. sudo /etc/init.d/redis-server restart

## 使用如下指令查看端口信息，如信息如下则代表redis服务远程可访问
5. sudo  netstat -nlt|grep 6379

tcp        0      0 0.0.0.0:6379            0.0.0.0:*               LISTEN     
tcp6       0      0 :::6379                 :::*                    LISTEN
 
```

### 数据库表的创建

将doc/txlcn-demo.sql内的信息导入到数据库中

## 依赖包

```groovy
//lcn-tm依赖
implementation 'com.codingapi.txlcn:txlcn-tm:5.0.2.RELEASE'
```

## 配置信息

#tx中大部分的配置信息都存在默认值，若需要更改则在配置文件内重定义就好

```yaml
spring.application.name=TransactionManager
server.port=9991
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/tx-manager?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto=update

#mybatis.configuration.map-underscore-to-camel-case=true
#mybatis.configuration.use-generated-keys=true

#开启日志，同时在数库据中创建日志表
tx-lcn.logger.enabled=true
tx-lcn.logger.driver-class-name=${spring.datasource.driver-class-name}
tx-lcn.logger.jdbc-url=${spring.datasource.url}
tx-lcn.logger.username=${spring.datasource.username}
tx-lcn.logger.password=${spring.datasource.password}

logging.level.root=DEBUG

# 将host值写成具体的IP地址，方便不同PC连接该事务中心
tx-lcn.manager.host=192.168.4.17

tx-lcn.manager.port=9999

#事务中心的web端管理密码
tx-lcn.manager.admin-key=123456

#指定redis服务器的地址，不指定则为本地
#spring.redis.host=

# 异常回调开关。开启时请制定ex-url
#tx-lcn.manager.ex-url-enabled=true
#tx-lcn.manager.ex-url=/provider/email-to/1742838987@qq.com
#

## 分布式事务执行总时间(ms). 默认为36000
tx-lcn.manager.dtx-time=800000

# 参数延迟删除时间单位ms  默认为dtx-time值
tx-lcn.message.netty.attr-delay-time=${tx-lcn.manager.dtx-time}

# 事务处理并发等级. 默认为机器逻辑核心数5倍
tx-lcn.manager.concurrent-level=160

# 分布式事务锁超时时间 默认为-1，当-1时会用tx-lcn.manager.dtx-time的时间
tx-lcn.manager.dtx-lock-time=3600000

```

## 启动类上加注解 

```kotlin

@SpringBootApplication
@EnableTransactionManagerServer
class TmServiceApplication

```

##  web端访问管理中心
```html
# 访问地址
http://127.0.0.1:7970/admin/index.html

# 默认密码
codingapi

```



 
