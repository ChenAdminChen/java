package com.chen.redis.mapper;


import com.chen.redis.module.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;

//@CacheNamespace(implementation = RedisCache.class)
@Mapper
//@CacheEvict(cacheNames = "piDecimals",allEntries=true)
//@Cacheable("piDecimals")
public interface TeacherMapper {
    @Insert("insert into teacher(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int add(Teacher teacher);

//    @CacheEvict(cacheNames = {"piDecimals","selectById"},key = "#teacher.id",allEntries = true)

//    @Caching(put = {@CachePut(cacheNames = "selectById", key = "#p0.id")}, evict = @CacheEvict(value = "piDecimals",allEntries = true))

    //@CachePut -> 修改 将返回值更新到缓存中
    //mybatis中的update return count,but value is teacher
    @Caching(evict = {@CacheEvict(value =  "piDecimals",allEntries = true), @CacheEvict(cacheNames = "selectById", key = "#p0.id")})
    @Update("update teacher set name = #{name} where id = #{id}")
    int update(Teacher teacher);

    @CacheEvict(cacheNames = {"piDecimals", "selectById"})
    @Update("update teacher set name = #{name} where id = #{id}")
    int updateNotAllEntries(Teacher teacher);

    @Cacheable(value = "piDecimals")
    @Select("select * from teacher")
    List<Teacher> select();

    //方法中只存在一个参数时 该参数就写入key
    @Cacheable(cacheNames = "selectById")
    @Select("select * from teacher where id = #{id}")
    Teacher selectById(Integer id);

    @Delete("delete from teacher where id = #{id}")
    @CacheEvict(cacheNames = "piDecimals", allEntries = true)
    int delete(Integer id);

}
