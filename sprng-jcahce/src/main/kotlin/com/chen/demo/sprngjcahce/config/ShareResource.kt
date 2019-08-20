package com.chen.demo.sprngjcahce.config

import org.ehcache.config.builders.CacheConfigurationBuilder
import org.ehcache.config.builders.CacheManagerBuilder
import org.ehcache.config.builders.ResourcePoolsBuilder
import org.ehcache.expiry.Duration
import org.ehcache.expiry.Expirations
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit


@Configuration
@EnableCaching
class CachingConfig {
    @Bean
    fun cacheManager(): CacheManager {
        return ConcurrentMapCacheManager("user")
    }


}

object ShareResource {

    var sessions = HashMap<Int, RoleAccessControl>()

    // 短信有效时间，单位分
    val expireTime: Long = 5
    // ehcache缓存初始化
    val cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true)

    // TODO 失效时间以后改成可配置
    val cacheConfiguration = CacheConfigurationBuilder
            .newCacheConfigurationBuilder(String::class.java, String::class.java,
                    ResourcePoolsBuilder.heap(10000))
            .withExpiry(Expirations.timeToLiveExpiration(Duration.of(expireTime,
                    TimeUnit.MINUTES))).build()!!

    val myCache = cacheManager.createCache("myCache", cacheConfiguration)

    /**
     * get access control from cache
     *     get access control from database and store it in cache if it's not exist
     *
     * WARNING: will not update access control
     */
//    fun getAccessControl(id: Int): RoleAccessControl? {
//        var accessControl: RoleAccessControl? = sessions[id]
//        if (accessControl == null) {
//            accessControl = AccessManagement.getInstance().getAccessControl(id)
//            if (accessControl != null)
//                sessions[id] = accessControl
//        }
//
//        return accessControl
//    }
}