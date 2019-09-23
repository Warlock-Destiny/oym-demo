package com.cn.dao;

import com.cn.entity.TestZhangyd;
import org.springframework.stereotype.Repository;

@Repository
public interface TestZhangydDao {

    TestZhangyd getById(Long id);

}
