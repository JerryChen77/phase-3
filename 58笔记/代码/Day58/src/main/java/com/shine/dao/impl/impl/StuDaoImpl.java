package com.shine.dao.impl.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.shine.dao.StuDao;
import com.shine.entity.Stu;

public class StuDaoImpl implements StuDao {

    private DruidDataSource dataSource;

    public DruidDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DruidDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Stu findStuById(Integer id) {
        return null;
    }
}
