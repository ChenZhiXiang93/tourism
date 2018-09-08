package com.bonc.mapper;

import com.bonc.pojo.Tourism;
import com.bonc.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 10:42 2018/8/24
 * @Modified By:
 */
public interface UserMapper {

    User getUserByName(@Param("username") String username);

}
