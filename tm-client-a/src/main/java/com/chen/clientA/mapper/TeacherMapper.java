package com.chen.clientA.mapper;


import com.chen.clientA.module.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TeacherMapper {
    @Insert("insert into teacher(name) values(#{name})")
    @Options(useGeneratedKeys = true,  keyProperty = "id")
    int add(Teacher teacher);

    @Update("update teacher set name = #{name} where id = #{id}")
    int udpate(Teacher teacher);
}
