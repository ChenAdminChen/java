## Resources
### UserApi

用戶身份管理操作API

#### getUserInfo
```
GET /user
```

##### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|User|
|201|post ok|No Content|
|403|Forbidden!|No Content|
|404|not found resources|No Content|
|417|检查出数据有问题|No Content|
|422|请求格式正确，请求中可能存在语法问题|No Content|
|500|500 message|Error|


##### Produces

* */*

### Basic-error-controller

Basic Error Controller

#### error
```
GET /error
```

##### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|object|
|201|post ok|No Content|
|403|Forbidden!|No Content|
|404|not found resources|No Content|
|500|500 message|Error|


##### Produces

* */*

#### error
```
PUT /error
```

##### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|object|


##### Consumes

* application/json

##### Produces

* */*

#### error
```
POST /error
```

##### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|object|


##### Consumes

* application/json

##### Produces

* */*

#### error
```
DELETE /error
```

##### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|object|


##### Produces

* */*

#### error
```
PATCH /error
```

##### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|object|


##### Consumes

* application/json

##### Produces

* */*

#### error
```
HEAD /error
```

##### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|object|


##### Consumes

* application/json

##### Produces

* */*

#### error
```
OPTIONS /error
```

##### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|object|


##### Consumes

* application/json

##### Produces

* */*

