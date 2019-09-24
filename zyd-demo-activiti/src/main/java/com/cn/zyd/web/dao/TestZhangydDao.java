package com.cn.zyd.web.dao;

import com.cn.zyd.web.entity.TestZhangyd;
import org.springframework.stereotype.Repository;

@Repository
public interface TestZhangydDao {

    TestZhangyd getById(Long id);

}
