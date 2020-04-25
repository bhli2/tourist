package com.qbk.sql.mybatisplus.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qbk.sql.mybatisplus.domain.TabUser;
import com.qbk.sql.mybatisplus.mapper.TabUserMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TabUserService extends ServiceImpl<TabUserMapper, TabUser>{

    @Transactional
    public TabUser testTransaction(TabUser user) {
        TabUser user1 = this.getOne(Wrappers.<TabUser>lambdaQuery().eq(TabUser::getUserName,user.getUserName()));
        System.out.println(user1);
        final boolean insert = this.save(user);
        System.out.println("插入结果：" + insert);
        user1 = this.getOne(Wrappers.<TabUser>lambdaQuery().eq(TabUser::getUserName,user.getUserName()),false);
        System.out.println(user1);
        //int i = 10 /0 ;
        return user1;
    }
}
