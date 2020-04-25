package com.qbk.sql.transaction.mapper;

import com.qbk.sql.transaction.domain.TabUser;

/**
* Created by Mybatis Generator 2020/04/25
*/
public interface TabUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(TabUser record);

    int insertSelective(TabUser record);

    TabUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(TabUser record);

    int updateByPrimaryKey(TabUser record);
}