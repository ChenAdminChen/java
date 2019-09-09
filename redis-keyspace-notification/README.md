# redis keyspace notifications

## stop etc/init.d/redis

```command
sudo /etc/init.d/redis-server stop
```

## redis keyspace notifications

[redis keyspace notifications learn address](https://redis.io/topics/notifications)

### redis server unlock keyspace notifications

nano redis.cof
```
notify-keyspace-events Ex
```

### redis client config keyspace notifications

```command
redis-cli config set notify-keyspace-events Klg
```

### redis client subscribe

```command
redis-cli --csv psubscribe '__key*__:*'
```

### redis client expire

```command
redis-cli
set redis "test"
expire redis 10
```

## spring boot redis keyspace notification example

[spring-boot-redis keyspace notification address](https://www.techsumm.com/technical-blogs/redis-technical-blogs/redis-keyspace-notifications-using-spring-boot-application/)


