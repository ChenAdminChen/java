## 项目的技术

### JMS连接

### JMS连接失败后重连

### 监听JMS连接状态，若失败后自动重连

### JMS收发信息

### Thread线程等待

### 线程池

## 项目类中的说明

### JMSConnectionInfo.java
    
    保存JMS连接信息，及连接成功后保存session
    
### JMSConnectRunnable.java
    
    JMS连接，重连
    
### JMSConsumer.java
    
    JMS连接成功后，订阅topic，发送相应的消息到特定topic

### SendJms.java

    main方法，启动项目，同时发送消息
    
## 其他class用于测试
