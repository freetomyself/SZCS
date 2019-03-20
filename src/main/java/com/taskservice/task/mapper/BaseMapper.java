package com.taskservice.task.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: task--com.taskservice.task.mapper
 * @author: WaHotDog 2019-02-28 09:40
 **/


public interface BaseMapper<T, E> {
    long countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(E id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(E example);

    T selectByPrimaryKey(E id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
