# 权限设计文档
    
## 需求

1.每个spring-boot项目都应拥有属于自己的权限管理，用户与权限之间可进行配置，并且每个项目之间的权限不相互影响    
2.将功能相近的权限组合成权限模块  
3.每个模块下面存在读（read）写(write)两个具体的权限动作  
4.权限模块有可能存在父权限模块，其子模块可继承父模块的权限，子模块的模块的权限不能大于父模块的权限  
并且父子模块的关系可形成结构树，便于前端用户对权限进行分配  
5.权限模块有可能依赖其他权限模块的权限，当所依赖的权限受到限制时，其该模块权限也不可使用  
6.用户与权限的关系应基于RBAC（Role-Based Access Control，基于角色的访问控制）用户角色权限理论进行扩展  
7.当用户赋权后，在访问接口时后台应使用spring-security的注解(@PreAuthorize("hasAuthorize('')"))进行权限检查，认证通过后才继续访问  

## 实现

### 实现需求所依赖的技术

>使用spring的spring-security模块  

1.spring-security模块进行权限检查，其权限检查的注解作用于方法，只有访问该方法时才可进行权限检查  
    
>java自定义注解  

1.在方法上使用自定义的注解，注解描述每个方法所需要的权限，及所权限依赖关系  
2.项目编译时扫描自定义的注解，将权限之间的关系转化成sql语句  

>mysql数据库

1.将描述权限关系的sql语句插入到数据库中  
2.存储用户、角色之间的关系  
3.存储角色、权限之间的关系  

### 具体实现的方案

#### 数据库表的设计方案

>service

使用权限模块进行自身权限管理的模块，首先需要将自己唯一识别的编码添加到数据库中,便于后面的权限绑定到项目中
 
 |  字段名   |    类型   |      描述    |
 |--------- |----------|-------------|
 |   id     |    int   | 自增长的主键   |
 |  name    |  varchar |  项目唯一标识符 |
 
```sql
create table service(
   id int not null,
    name VARCHAR(100) not null,
   PRIMARY KEY (id)
);

```

>角色表(role)

该表用于存储系统中的角色，其系统可对该表进行增、删、改、查的操作  

|  字段名   |    类型   |      描述    |
|--------- |----------|-------------|
|   id     |   int    | 自增长的主键  |
|  name    |  varchar | 角色名字     |

```sql
create table role(
   id int not null AUTO_INCREMENT,
   name VARCHAR(20) not null,
   PRIMARY KEY (id),
);
```

>权限表(authority)

该表描述系统中权限模块及权限模块的父模块的关系，一个权限只有一个父类    
表的内容来自软件编译时生产的sql语句  
系统不可进行增、删的操作，可对表中的name进行修改操作  

|  字段名   |    类型   |      描述    |
|--------- |----------|-------------|
|   id     |    int   |  自增长的主键 |
|   code   |  varchar | 权限模块名字  |
|  parent  |   int    |  父模块id    |
|service_id|   int    | 服务模块id   |
|  name    |  varchar |  描述权限模块模块的名字，用于管理人员识别    |

```sql
create table authority(
   id int not null AUTO_INCREMENT,
   code VARCHAR(100) not null,
   service_id int not null,
   parent int ,
   name VARCHAR(100),
   PRIMARY KEY (id),
   FOREIGN KEY (service_id) REFERENCES service(id)
);
```

>权限依赖关系表(dependency)

描述某权限依赖某权限的那个行为，某权限可依赖多个权限  

|  字段名   |    类型   |      描述    |
|--------- |----------|-------------|
|  authority_id | int | 权限id    |
| dependency_id  |int |  权限依赖的权限id   |
| aut_action_id   | int   | 权限行为表的id |
| dep_action_id   | int   | 权限行为表的id |

```sql
create table dependency(
   authority_id  int not null,
   dependency_id int not null,
   aut_action_id int not null,
   dep_action_id int not null,
   PRIMARY KEY (authority_id,dependency_id),
   FOREIGN KEY (authority_id) REFERENCES authority(id),
   FOREIGN KEY (dependency_id) REFERENCES authority(id),
   FOREIGN KEY (aut_action_id) REFERENCES action(id),
   FOREIGN KEY (dep_action_id) REFERENCES action(id)
);
```

>权限行为表(action)

该表用于描述权限的具体行为，是对某张表的数据是进行增/删/修改权限(write)，还是只有查看的权限（read），该表的数据也是从项目中获取

|  字段名   |    类型   |      描述    |
|--------- |----------|-------------|
|   id     |    int   |  自增长的主键 |
|   name   |  varchar |  权限动作行为（read,write) |

```sql
create table action(
    id int not null auto_increment,
    name VARCHAR(100)
    PRIMARY KEY (id)
)
```

>角色与权限关系表(role_authority)

描述角色与权限的关系，该角色对某权限有什么操作的权限  

|  字段名   |    类型   |      描述    |
|--------- |----------|-------------|
|  role_id |   int    | 角色表的id    |
|  authority_id  | int |  权限表的id   |
|  action_id    |    int | 权限行为表的id,-1:继承，0：无 |

```sql

create table role_authority(
   role_id int not null,
   authority_id int not null,
   action_id int not null,
   FOREIGN KEY (authority_id) REFERENCES authority(id),
   FOREIGN KEY (role_id) REFERENCES role(id),
   PRIMARY KEY (role_id,authority_id)
);

```

>用户与角色关系表(user_role)

描述具用户对角色的关系，以便用户通过该表可获得具体的权限  

|  字段名   |    类型   |      描述    |
|--------- |----------|-------------|
| role_id |   int    | 角色表的id    |
| user_id  | int      |  用户表的id   |

```sql
create table user_role(
   user_id int not null,
   role_id int not null,
   PRIMARY KEY (role_id,user_id),
   -- FOREIGN KEY (user_id) REFERENCES user(id),
   FOREIGN KEY (role_id) REFERENCES role(id)
);
```

#### 使用spring-security自带的注解

>@PreAuthorize  

1.该注解用于描述使用某方法时所要的权限   
2.@PreAuthorize中的内容可描述该权限的父类，同时可分级描述父子关系，以"."进行区分  
3.格式：@PreAuthorize("hasAuthority('[xxx.{...}].read')"),@PreAuthorize("hasAuthority('[xxx.{...}].write')")   
4.在自定义注解的项目内添加spring-security的依赖
5.实例
```
@PreAuthorize("hasAuthority('user.test.device.read')")

@PreAuthorize("hasAuthority('device.read')")

@PreAuthorize("hasAuthority('company.read')")
```

|   id    |  code                   |    parent   |
|---------|-------------------------|-------------|
|权限模块id |  权限模块名字，由注解中生成  |权限模块的父类  |
|   1     | user                    |     null    |
|   2     | user.test               |     1       |
|   3     | user.test.device        |     2       |
|   4     | device                  |     null    |
|   5     | company                 |     null    |


#### 自定义java注解

java中提供了自定义注解的功能，因此根据java语法自定义如下注解  

>@PermissionDependency() 

1.该注解用于描述使用某个权限时，它所依赖的权限，一个权限可依赖多个
2.使用@PermissionDependency将权限注解到类上,表示该类中的每个方法上的权限都依赖于该权限
3.使用@PermissionDependency将权限注解到方法上，表示该方法上的权限依赖于该权限
4.一个方法它所依赖的权限，由2、3的权限组成
5.格式：@PermissionDependency({"xxx.read",...})
6.实例  
```
@PermissionDependency({"device.read","user.test.device.read"})
@PreAuthorize("hasAuthority('company.read')")
```
|   code_id    |         dependency_id      |   action     |
|--------------|----------------------------|--------------|
|权限模块id      |  权限依赖的模块id             |依赖什么操作    | 
|   5          |   4                        |     read     |
|   5          |   2                        |     read     |

>如何使用注解实例

```java
//注解在class
@PermissionDependency({"test.read"})
public class UserInfoService {

//    @Role("normal")
    @PreAuthorize("hasAuthority('user.read')")
    public void getUser() {

    }

    //    @Role("admin")
    @PreAuthorize("hasAuthority('test.read')")
    public void updateUser() {
    }

    //    @Role("admin")
    @PreAuthorize("hasAuthority('user.test.read')")
    public void updateUser2() {
    }

//    code:test 1
//    code:test.user 2 1
//    code:test.user.extra 3 2

    @PreAuthorize("hasAuthority('test.user.extra.read')")
    @PermissionDependency({"specialist.read"})
    public void getFollowed() {

    }

    @PreAuthorize("hasAuthority('user.extra.read')")
    @PermissionDependency({"department.read"})
    public void getDepartment() {
    }
}
    
```

>@Service()

1.该注解使用标识项目的唯一码，数据库进行存储
2.将项目的唯一码与自己的权限进行关联，用于区分各个模块中的权限名相同的权限
3.格式:@Service("xxxx")
3.实例

```java
@Service(System.value)    //用于编译时读取
@Component     //用于运行时读取
public class System {

    private String serviceName ;

    public static  final String value = "test1";

    public System() {
        this.serviceName = value;
    }

    public String getServiceName() {
        return serviceName;
    }
}

```

#### 权限模块管理
    每个spring-boot的项目都有属于自己的权限，而权限模块管理用于管理所有在权限模块进行过注册的模块的权限

