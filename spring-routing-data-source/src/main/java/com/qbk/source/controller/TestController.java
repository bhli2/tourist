package com.qbk.source.controller;

import com.qbk.source.config.datasource.DBContextHolder;
import com.qbk.source.config.datasource.DynamicDataSource;
import com.qbk.source.domain.DateSource;
import com.qbk.source.domain.TabUser;
import com.qbk.source.service.TabUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class TestController {
    /**
     * 动态数据源
     */
    private DynamicDataSource dynamicDataSource ;
    private TabUserService tabUserService;
    private TestController(TabUserService tabUserService,DynamicDataSource dynamicDataSource){
        this.tabUserService = tabUserService ;
        this.dynamicDataSource = dynamicDataSource ;
    }

    /**
     * 切换数据源
     */
    @GetMapping("/")
    public List<TabUser> getTest1(
            @RequestParam(value = "dataSourceName")String dataSourceName){
        //切换数据源
        DBContextHolder.setDataSource(dataSourceName);
        return tabUserService.selectList();
    }

    /**
     * 默认数据源
     */
    @GetMapping("/test")
    public List<TabUser> getTest2(){
        //无数据源
        return tabUserService.selectList();
    }

    /**
     * 查询数据源
     */
    @GetMapping("/get")
    public Set<Object> get(){
        return dynamicDataSource.getDynamicTargetDataSources().keySet();
    }

    /**
     * 添加数据源
     */
    @PostMapping("/add")
    public boolean add(@RequestBody DateSource dateSource){
        return dynamicDataSource.createDataSource(dateSource);
    }

    /**
     * 删除数据源
     */
    @DeleteMapping("/delete")
    public boolean delete( @RequestParam(value = "dataSourceName")String dataSourceName){
        return dynamicDataSource.delDatasources(dataSourceName);
    }
}
