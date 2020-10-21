package com.oym.base.config;

import com.oym.base.db.config.DbConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zyd
 * @date 2019/9/25 14:59
 * @desc
 */
@Configuration
@Import(DbConfiguration.class)
public class BaseConfiguration {

}
