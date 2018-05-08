package com.yf.af.data.mapper;

import com.yf.af.data.model.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Property;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.ehcache.EhcacheCache;

import java.util.List;

/**
 * Created by chen on 2018/5/7.
 */
@CacheNamespace(implementation = EhcacheCache.class,properties = {
        @Property(name="timeToIdleSeconds",value = "10"),   //多久没有访问就失效
        @Property(name="timeToLiveSeconds",value = "10"),   //失效时间
        @Property(name="maxEntriesLocalHeap",value = "1000"),
        @Property(name="maxEntriesLocalDisk",value = "10000000"),
        @Property(name="memoryStoreEvictionPolicy",value = "LRU")
})

public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();
}
