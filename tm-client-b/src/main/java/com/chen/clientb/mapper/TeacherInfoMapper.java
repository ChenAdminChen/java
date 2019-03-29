package com.chen.clientb.mapper;

import com.chen.clientb.module.TeacherInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TeacherInfoMapper {
    @Insert("insert into teacher_info(t_id,info) values(#{tId},#{info})")
//    @Options(useGeneratedKeys = true,  keyProperty = "id")
    int add(TeacherInfo teacherInfo);

    @Update("update teacher_info set info= #{info} where t_id = #{tId}")
    int update(TeacherInfo teacherInfo);
}
