#分布式事务
 
 ## 事务
 
 &nbsp;&nbsp;&nbsp;&nbsp;事务提供一种机制将一个活动涉及的所有操作纳入到一个不可分割的执行单元，组成事务的所有操作只有在所有操作均能正常
 执行的情况下方能提交，只要其中任一操作执行失败，都将导致整个事务的回滚。简单地说，事务提供一种“要么什么都不做，
 要么做全套（All or Nothing）”机制。
  
 ## 分布式事务
 
 &nbsp;&nbsp;&nbsp;&nbsp;分布式事务就是指事务的参与者、支持事务的服务器、资源服务器以及事务管理器分别位于不同的分布式系统的不同节点之上。
 简单的说，就是一次大的操作由不同的小操作组成，这些小的操作分布在不同的服务器上，且属于不同的应用，分布式事务需要
 保证这些小操作要么全部成功，要么全部失败。本质上来说，分布式事务就是为了保证不同数据库的数据一致性。

## XA协议（eXtended Architecture）
    XA协议是X/Open（the Open Group）提出的,为解决分布式事务处理（distrubuted-transaction-processing）的方案，在XA协议中使用了2pc理论实现事务管理机制，它将一个应用程序的执行流程分为如下几种：
1. 应用程序（AP:application program）
2. 事务管理器（TM:transaction management）
3. 资源管理器(RM:resource management)
4. 通信资源管理器(CRM:communication resource management)  
  
&nbsp;&nbsp;&nbsp;&nbsp;通常把一个数据库内部的事务处理，如对多个表的操作，作为本地事务看待。数据库的事务处理对象是本地事务
，而分布式事务处理的对象是全局事务。   所谓全局事务，是指分布式事务处理环境中，多个数据库可能需要共同完成一个工作，这个工作即是
一个全局事务，例如，一个事务中可能更新几个不同的数据库。对数据库的操作发生在系统的各处但必须全部被提交或回滚。此时一个数据库对自己
内部所做操作的提交不仅依赖本身操作是否成功，还要依赖与全局事务相关的其它数据库的操作是否成功，如果任一数据库的任一操作失败，则参与此
事务的所有数据库所做的所有操作都必须回滚。  
&nbsp;&nbsp;&nbsp;&nbsp;一般情况下，某一数据库无法知道其它数据库在做什么，因此，在一个 DTP 环境中，交易中间件是必需的，
由它通知和协调相关数据库的提交或回滚。而一个数据库只将其自己所做的操作（可恢复）影射到全局事务中。   

&nbsp;&nbsp;&nbsp;&nbsp;XA 就是 X/Open DTP 定义的交易中间件与数据库之间的接口规范（即接口函数），交易中间件用它来通知数据库事务的开始、结束以及提交、回滚等。 XA 接口函数由数据库厂商提供。 

## 2PC（Two Phase Commitment Protocol）

&nbsp;&nbsp;&nbsp;&nbsp;二阶段提交(Two-phaseCommit)是指，在计算机网络以及数据库领域内，为了使基于分布式系统架构下的所有节点在进行事务提交时保持一致性而设计的一种算法(Algorithm)。通常，二阶段提交也被称为是一种协议(Protocol))。在分布式系统中，每个节点虽然可以知晓自己的操作时成功或者失败，却无法知道其他节点的操作的成功或失败。当一个事务跨越多个节点时，为了保持事务的ACID特性，需要引入一个作为协调者的组件来统一掌控所有节点(称作参与者)的操作结果并最终指示这些节点是否要把操作结果进行真正的提交(比如将更新后的数据写入磁盘等等)。因此，二阶段提交的算法思路可以概括为：参与者将操作成败通知协调者，再由协调者根据所有参与者的反馈情报决定各参与者是否要提交操作还是中止操作。

所谓的两个阶段是指：第一阶段：准备阶段(投票阶段)和第二阶段：提交阶段（执行阶段）。

> 准备阶段  

事务协调者(事务管理器)给每个参与者(资源管理器)发送Prepare消息，每个参与者要么直接返回失败(如权限验证失败)，要么在本地执行事务，写本地的redo和undo日志，但不提交，到达一种“万事俱备，只欠东风”的状态。

可以进一步将准备阶段分为以下三个步骤：

1）协调者节点向所有参与者节点询问是否可以执行提交操作(vote)，并开始等待各参与者节点的响应。

2）参与者节点执行询问发起为止的所有事务操作，并将Undo信息和Redo信息写入日志。（注意：若成功这里其实每个参与者已经执行了事务操作）

3）各参与者节点响应协调者节点发起的询问。如果参与者节点的事务操作实际执行成功，则它返回一个”同意”消息；如果参与者节点的事务操作实际执行失败，则它返回一个”中止”消息。

> 提交阶段  

如果协调者收到了参与者的失败消息或者超时，直接给每个参与者发送回滚(Rollback)消息；否则，发送提交(Commit)消息；参与者根据协调者的指令执行提交或者回滚操作，释放所有事务处理过程中使用的锁资源。(注意:必须在最后阶段释放锁资源)

接下来分两种情况分别讨论提交阶段的过程。

1）协调者节点向所有参与者节点发出”正式提交(commit)”的请求。

2）参与者节点正式完成操作，并释放在整个事务期间内占用的资源。

3）参与者节点向协调者节点发送”完成”消息。

4）协调者节点受到所有参与者节点反馈的”完成”消息后，完成事务。  


如果任一参与者节点在第一阶段返回的响应消息为”中止”，或者 协调者节点在第一阶段的询问超时之前无法获取所有参与者节点的响应消息时：

1）协调者节点向所有参与者节点发出”回滚操作(rollback)”的请求。

2）参与者节点利用之前写入的Undo信息执行回滚，并释放在整个事务期间内占用的资源。

3）参与者节点向协调者节点发送”回滚完成”消息。

4）协调者节点受到所有参与者节点反馈的”回滚完成”消息后，取消事务。


二阶段提交看起来确实能够提供原子性的操作，但是不幸的事，二阶段提交还是有几个缺点的：

1. 同步阻塞问题。执行过程中，所有参与节点都是事务阻塞型的。当参与者占有公共资源时，其他第三方节点访问公共资源不得不处于阻塞状态。

2. 单点故障。由于协调者的重要性，一旦协调者发生故障。参与者会一直阻塞下去。尤其在第二阶段，协调者发生故障，那么所有的参与者还都处于锁定事务资源的状态中，而无法继续完成事务操作。（如果是协调者挂掉，可以重新选举一个协调者，但是无法解决因为协调者宕机导致的参与者处于阻塞状态的问题）

3. 数据不一致。在二阶段提交的阶段二中，当协调者向参与者发送commit请求之后，发生了局部网络异常或者在发送commit请求过程中协调者发生了故障，这回导致只有一部分参与者接受到了commit请求。而在这部分参与者接到commit请求之后就会执行commit操作。但是其他部分未接到commit请求的机器则无法执行事务提交。于是整个分布式系统便出现了数据部一致性的现象。

4. 二阶段无法解决的问题：协调者再发出commit消息之后宕机，而唯一接收到这条消息的参与者同时也宕机了。那么即使协调者通过选举协议产生了新的协调者，这条事务的状态也是不确定的，没人知道事务是否被已经提交。

由于二阶段提交存在着诸如同步阻塞、单点问题、脑裂等缺陷，所以，研究者们在二阶段提交的基础上做了改进，提出了三阶段提交。

## 3PC（Three Phase Commitment Protocol）

三阶段提交（Three-phase commit），也叫三阶段提交协议（Three-phase commit protocol），是二阶段提交（2PC）的改进版本。

与两阶段提交不同的是，三阶段提交有两个改动点:
1. 引入超时机制。同时在协调者和参与者中都引入超时机制。
2. 在第一阶段和第二阶段中插入一个准备阶段。保证了在最后提交阶段之前各参与节点的状态是一致的。

也就是说，除了引入超时机制之外，3PC把2PC的准备阶段再次一分为二，这样三阶段提交就有CanCommit、PreCommit、DoCommit三个阶段。

> CanCommit阶段  

3PC的CanCommit阶段其实和2PC的准备阶段很像。协调者向参与者发送commit请求，参与者如果可以提交就返回Yes响应，否则返回No响应。

1. 事务询问 协调者向参与者发送CanCommit请求。询问是否可以执行事务提交操作。然后开始等待参与者的响应。
2. 响应反馈 参与者接到CanCommit请求之后，正常情况下，如果其自身认为可以顺利执行事务，则返回Yes响应，并进入预备状态。否则反馈No

> PreCommit阶段  

&nbsp;&nbsp;&nbsp;&nbsp;协调者根据参与者的反应情况来决定是否可以记性事务的PreCommit操作。根据响应情况，有以下两种可能。
&nbsp;&nbsp;&nbsp;&nbsp;假如协调者从所有的参与者获得的反馈都是Yes响应，那么就会执行事务的预执行。

1.发送预提交请求 协调者向参与者发送PreCommit请求，并进入Prepared阶段。

2.事务预提交 参与者接收到PreCommit请求后，会执行事务操作，并将undo和redo信息记录到事务日志中。

3.响应反馈 如果参与者成功的执行了事务操作，则返回ACK响应，同时开始等待最终指令。

&nbsp;&nbsp;&nbsp;&nbsp;假如有任何一个参与者向协调者发送了No响应，或者等待超时之后，协调者都没有接到参与者的响应，那么就执行事务的中断。

1.发送中断请求 协调者向所有参与者发送abort请求。

2.中断事务 参与者收到来自协调者的abort请求之后（或超时之后，仍未收到协调者的请求），执行事务的中断。

> doCommit阶段  

该阶段进行真正的事务提交，也可以分为以下两种情况。

1) 执行提交

1.发送提交请求 协调接收到参与者发送的ACK响应，那么他将从预提交状态进入到提交状态。并向所有参与者发送doCommit请求。

2.事务提交 参与者接收到doCommit请求之后，执行正式的事务提交。并在完成事务提交之后释放所有事务资源。

3.响应反馈 事务提交完之后，向协调者发送Ack响应。

4.完成事务 协调者接收到所有参与者的ack响应之后，完成事务。

2) 中断事务  
协调者没有接收到参与者发送的ACK响应（可能是接受者发送的不是ACK响应，也可能响应超时），那么就会执行中断事务。

1.发送中断请求 协调者向所有参与者发送abort请求

2.事务回滚 参与者接收到abort请求之后，利用其在阶段二记录的undo信息来执行事务的回滚操作，并在完成回滚之后释放所有的事务资源。

3.反馈结果 参与者完成事务回滚之后，向协调者发送ACK消息

4.中断事务 协调者接收到参与者反馈的ACK消息之后，执行事务的中断。

&nbsp;&nbsp;&nbsp;&nbsp;在doCommit阶段，如果参与者无法及时接收到来自协调者的doCommit或者rebort请求时，会在等待超时之后，会继续进行事务的提交。（其实这个应该是基于概率来决定的，当进入第三阶段时，说明参与者在第二阶段已经收到了PreCommit请求，那么协调者产生PreCommit请求的前提条件是他在第二阶段开始之前，收到所有参与者的CanCommit响应都是Yes。（一旦参与者收到了PreCommit，意味他知道大家其实都同意修改了）所以，一句话概括就是，当进入第三阶段时，由于网络超时等原因，虽然参与者没有收到commit或者abort响应，但是他有理由相信：成功提交的几率很大。 ）

### 2PC与3PC的区别

&nbsp;&nbsp;&nbsp;&nbsp;相对于2PC，3PC主要解决的单点故障问题，并减少阻塞，因为一旦参与者无法及时收到来自协调者的信息之后，他会默认执行commit。而不会一直持有事务资源并处于阻塞状态。但是这种机制也会导致数据一致性问题，因为，由于网络原因，协调者发送的abort响应没有及时被参与者接收到，那么参与者在等待超时之后执行了commit操作。这样就和其他接到abort命令并执行回滚的参与者之间存在数据不一致的情况。
了解了2PC和3PC之后，我们可以发现，无论是二阶段提交还是三阶段提交都无法彻底解决分布式的一致性问题。Google Chubby的作者Mike Burrows说过， there is only one consensus protocol, and that’s Paxos” – all other approaches are just broken versions of Paxos. 意即世上只有一种一致性算法，那就是Paxos，所有其他一致性算法都是Paxos算法的不完整版。后面的文章会介绍这个公认为难于理解但是行之有效的Paxos算法。

## CAP理论

&nbsp;&nbsp;&nbsp;&nbsp;一个分布式系统最多只能同时满足一致性（Consistency）、可用性（Availability）和分区容错性（Partition tolerance）这三项中的两项。

> 一致性（Consistency）  

一致性指“all nodes see the same data at the same time”，即更新操作成功并返回客户端完成后，所有节点在同一时间的数据完全一致。

> 可用性（Availability）  

可用性指“Reads and writes always succeed”，即服务一直可用，而且是正常响应时间。

> 分区容错性（Partition tolerance）  

分区容错性指“the system continues to operate despite arbitrary message loss or failure of part of the system”，即分布式系统在遇到某节点或网络分区故障的时候，仍然能够对外提供满足一致性和可用性的服务。

### CAP权衡:

&nbsp;&nbsp;&nbsp;&nbsp;通过CAP理论，我们知道无法同时满足一致性、可用性和分区容错性这三个特性，那要舍弃哪个呢？

&nbsp;&nbsp;&nbsp;&nbsp;对于多数大型互联网应用的场景，主机众多、部署分散，而且现在的集群规模越来越大，所以节点故障、网络故障是常态，而且要保证服务可用性达到N个9，即保证P和A，舍弃C（退而求其次保证最终一致性）。虽然某些地方会影响客户体验，但没达到造成用户流程的严重程度。一个分布式系统最多只能同时满足一致性（Consistency）、可用性（Availability）和分区容错性（Partition tolerance）这三项中的两项。

## Base理论

&nbsp;&nbsp;&nbsp;&nbsp;eBay的架构师Dan Pritchett源于对大规模分布式系统的实践总结，在ACM上发表文章提出BASE理论，BASE理论是对CAP理论的延伸，核心思想是即使无法做到强一致性（Strong Consistency，CAP的一致性就是强一致性），但应用可以采用适合的方式达到最终一致性（Eventual Consitency）。

BASE是指基本可用（Basically Available）、软状态（ Soft State）、最终一致性（ Eventual Consistency）。

> 基本可用（Basically Available）  

基本可用是指分布式系统在出现故障的时候，允许损失部分可用性，即保证核心可用。
电商大促时，为了应对访问量激增，部分用户可能会被引导到降级页面，服务层也可能只提供降级服务。这就是损失部分可用性的体现。

> 软状态（ Soft State）  

软状态是指允许系统存在中间状态，而该中间状态不会影响系统整体可用性。分布式存储中一般一份数据至少会有三个副本，允许不同节点间副本同步的延时就是软状态的体现。mysql replication的异步复制也是一种体现。

> 最终一致性（ Eventual Consistency）  

最终一致性是指系统中的所有数据副本经过一定时间后，最终能够达到一致的状态。弱一致性和强一致性相反，最终一致性是弱一致性的一种特殊情况。

## ACID理论 

ACID理论是指在数据库管理系统（DBMS）中事务所具有的四个特性：原子性（Atomicity）、一致性（Consistency）、隔离性（Isolation，又称独立性）、持久性（Durability）。

> 原子性  

&nbsp;&nbsp;&nbsp;&nbsp;事务必须是原子工作单元；对于其数据修改，要么全都执行，要么全都不执行。通常，与某个事务关联的操作具有共同的目标，并且是相互依赖的。如果系统只执行这些操作的一个子集，则可能会破坏事务的总体目标。原子性消除了系统处理操作子集的可能性。  

> 一致性  

&nbsp;&nbsp;&nbsp;&nbsp;事务在完成时，必须使所有的数据都保持一致状态。在相关数据库中，所有规则都必须应用于事务的修改，以保持所有数据的完整性。事务结束时，所有的内部数据结构（如 B 树索引或双向链表）都必须是正确的。某些维护一致性的责任由应用程序开发人员承担，他们必须确保应用程序已强制所有已知的完整性约束。例如，当开发用于转帐的应用程序时，应避免在转帐过程中任意移动小数点。  

> 隔离性   

&nbsp;&nbsp;&nbsp;&nbsp;由并发事务所作的修改必须与任何其它并发事务所作的修改隔离。事务查看数据时数据所处的状态，要么是另一并发事务修改它之前的状态，要么是另一事务修改它之后的状态，事务不会查看中间状态的数据。这称为可串行性，因为它能够重新装载起始数据，并且重播一系列事务，以使数据结束时的状态与原始事务执行的状态相同。当事务可序列化时将获得最高的隔离级别。在此级别上，从一组可并行执行的事务获得的结果与通过连续运行每个事务所获得的结果相同。由于高度隔离会限制可并行执行的事务数，所以一些应用程序降低隔离级别以换取更大的吞吐量。   

> 持久性   

&nbsp;&nbsp;&nbsp;&nbsp;事务完成之后，它对于系统的影响是永久性的。该修改即使出现致命的系统故障也将一直保持。 
 
###  ACID和BASE的区别与联系  

ACID是传统数据库常用的设计理念，追求强一致性模型。BASE支持的是大型分布式系统，提出通过牺牲强一致性获得高可用性。

ACID和BASE代表了两种截然相反的设计哲学

在分布式系统设计的场景中，系统组件对一致性要求是不同的，因此ACID和BASE又会结合使用。

## 柔性理论与刚性理论

## 事务产生的原因

> 多数据源产生事务（传统项目中连接不同的数据库）
 
解決方案：采用jta+ Atomikos实现 

> 微服务下的事务 

解決方案： LCN框架

[学习地址](https://txlcn.org/zh-cn/docs/setting/distributed.html)

## LCN 介绍

LCN解释：  
&nbsp;&nbsp;1. 锁定事务单元（lock）  
&nbsp;&nbsp;2. 确认事务模块状态(confirm)  
&nbsp;&nbsp;3. 通知事务(notify)  

&nbsp;&nbsp;LCN并不生产事务，LCN只是本地事务的协调工

&nbsp;&nbsp;TX-LCN定位于一款事务协调性框架，框架其本身并不操作事务，而是基于对事务的协调从而达到事务一致性的效果。

### LCN框架內实现的事务模式

1. LCN事务模式  
一、原理介绍:  
&nbsp;&nbsp;LCN模式是通过代理Connection的方式实现对本地事务的操作，然后在由TxManager统一协调控制事务。当本地事务提交回滚或者关闭连接时将会执行假操作，该代理的连接将由LCN连接池管理。  

二、模式特点:     
1). 该模式对代码的嵌入性为低。    
2). 该模式仅限于本地存在连接对象且可通过连接对象控制事务的模块。  
3). 该模式下的事务提交与回滚是由本地事务方控制，对于数据一致性上有较高的保障。  
4). 该模式缺陷在于代理的连接需要随事务发起方一共释放连接，增加了连接占用的时间。  

2. TCC事务模式  
一、原理介绍:      
&nbsp;&nbsp;TCC事务机制相对于传统事务机制（X/Open XA Two-Phase-Commit），其特征在于它不依赖资源管理器(RM)对XA的支持，而是通过对（由业务系统提供的）业务逻辑的调度来实现分布式事务。主要由三步操作，Try: 尝试执行业务、 Confirm:确认执行业务、 Cancel: 取消执行业务。  

二、模式特点:    
1). 该模式对代码的嵌入性高，要求每个业务需要写三种步骤的操作。  
2). 该模式对有无本地事务控制都可以支持使用面广。  
3). 数据一致性控制几乎完全由开发者控制，对业务开发难度要求高。  

3. TXC事务模式    
一、原理介绍：  
&nbsp;&nbsp;TXC模式命名来源于淘宝，实现原理是在执行SQL之前，先查询SQL的影响数据，然后保存执行的SQL快走信息和创建锁。当需要回滚的时候就采用这些记录数据回滚数据库，目前锁实现依赖redis分布式锁控制。

二、模式特点:  
1）. 该模式同样对代码的嵌入性低。  
2). 该模式仅限于对支持SQL方式的模块支持。  
3). 该模式由于每次执行SQL之前需要先查询影响数据，因此相比LCN模式消耗资源与时间要多。  
4). 该模式不会占用数据库的连接资源。  

### transaction manager center

&nbsp;&nbsp;&nbsp;&nbsp;TxManager作为分布式事务的控制方，管理事物組的创建及销毁

### redis在事务控制中心的作用

1. 事务控制中心注册到redis服务器中，通过redis服务的发布与订阅功能广播事务控制中心的运行相关消息
2. 事务控制中心将事物組相关的的消息存入在redis缓存

区分事务控制中心是否属于同一个分布式事务中心主要看TxManager下的连接的db资源(mysql 、redis)

### 事务中心的搭建

1. 安装TM需要依赖的中间件： JRE1.8+, Mysql5.6+, Redis3.2+

2. 以下的实现方式基于lcn的5.0.2.RELEASE版本

>  redis服务器的搭建  

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

> 数据库表的创建  

将doc/txlcn-demo.sql内的信息导入到数据库中

> 依赖包  

```groovy
//lcn-tm依赖
implementation 'com.codingapi.txlcn:txlcn-tm:5.0.2.RELEASE'
```

> 配置信息  

```properties
#tx中大部分的配置信息都存在默认值，若需要更改则在配置文件内重定义就好

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

> 启动类上加注解   

```kotlin

@SpringBootApplication
@EnableTransactionManagerServer
class TmServiceApplication

```

>  web端访问管理中心  
```html
# 访问地址
http://127.0.0.1:7970/admin/index.html

# 默认密码
codingapi

```

### tx-client

&nbsp;&nbsp;事务发起方或者参与反都由TxClient端来控制  
  
1. 实现Tx-client需要依赖组件： JRE1.8+,服务的注册中心，mybatis   
2. 以下的实现方式基于lcn的5.0.2.RELEASE版本，spring-cloud的Finchley.SR1
3. 使用了LCN中的lcn模式

### 搭建基于spring cloud的分布式事务项目

> 依赖  

```groovy
   //feign
    compile('org.springframework.cloud:spring-cloud-starter-openfeign')

  //tx
    compile 'com.codingapi.txlcn:txlcn-tc:5.0.2.RELEASE'
    compile 'com.codingapi.txlcn:txlcn-txmsg-netty:5.0.2.RELEASE'

   //eureka
    compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-client')

```

> 配置  

```yaml

# 注册中心的地址
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/

#设置feign超时时间
feign:
#  hystrix:
#    enabled: true
  client:
    config:
      default:
        connectTimeout: 500000
        readTimeout: 500000
        loggerLevel: basic

#Ribbon的负载均衡策略
#ribbon:
#  NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
#  MaxAutoRetriesNextServer: 0

server:
  port: 8763

spring:
  application:
    name: tm-client-a
  datasource:
    url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
  jpa:
    show-sql: true

#被调用方的url
tm-client-url: http://localhost:8764

logging:
  level:
    root: DEBUG

tx-lcn:
  client:
    manager-address: 192.168.4.17:9999
```

> 启动类的配置  

```kotlin

@SpringBootApplication
@EnableFeignClients  //feign便于访问其它应用的restful服务
@EnableEurekaClient  //注册eureka服务
@EnableDistributedTransaction  //开启事务
class TmClientAApplication

```

> 启用事务   

对于需要开启事务的方法前面加上注解就行，如下代码

```java
@LcnTransaction //使用lcn的模式处理事务
@Transactional  //开启本地事务 
public String updateTeacherClientAErrorClientBSuccess(@PathVariable("id") Integer id) {

        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName("update client-a");

        teacherMapper.udpate(teacher);

        String str = clientBService.updateClientBTransactionError(teacher.getId());

        throw new RuntimeException("client-a-error and client-b-success");
    }
    
```