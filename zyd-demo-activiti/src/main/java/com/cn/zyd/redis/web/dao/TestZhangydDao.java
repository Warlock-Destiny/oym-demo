package com.cn.zyd.redis.web.dao;

import com.cn.zyd.redis.web.entity.TestZhangyd;
import org.springframework.stereotype.Repository;

@Repository
public interface TestZhangydDao {

    TestZhangyd getById(Long id);

}
