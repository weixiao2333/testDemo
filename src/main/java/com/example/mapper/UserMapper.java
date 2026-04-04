package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ??? Mapper ??
 */
@Mapper
public interface UserMapper {
    
    /**
     * ? ID ???
     */
    User selectById(@Param("id") Long id);
    
    /**
     * ??????
     */
    List<User> selectAll();
    
    /**
     * ???????
     */
    List<User> selectByUsername(@Param("username") String username);
    
    /**
     * ???
     */
    int insert(User user);
    
    /**
     * ???
     */
    int update(User user);
    
    /**
     * ? ID ??
     */
    int deleteById(@Param("id") Long id);
}
