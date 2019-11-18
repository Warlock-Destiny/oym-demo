package com.cn.zyd.common.util.redis;

import com.cn.zyd.common.redis.RedisUtil;
import com.cn.zyd.common.util.TestMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zyd
 * @date 2019/11/18 11:21
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestMain.class)
public class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testLock() {
        System.out.println(redisUtil.tryLock("key1", "111", 200000));
        System.out.println(redisUtil.tryLock("key1", "111", 200000));
        System.out.println(redisUtil.tryUnLock("key1", "111"));
    }

}
