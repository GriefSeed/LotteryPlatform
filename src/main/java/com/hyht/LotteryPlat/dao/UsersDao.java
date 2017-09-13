package com.hyht.LotteryPlat.dao;

import com.hyht.LotteryPlat.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao {
    @Select("select * from users where user_id=#{userId}")
    Users queryUserById(@Param("userId") int userId);
}
