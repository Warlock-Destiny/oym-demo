package com.oym.base.db.dao;

import com.oym.base.db.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: zhangyd
 * @date: 2020/10/21 10:25
 */
@Repository
public interface SysUserDao extends BaseDao<SysUser> {

    List<SysUser> demo();

}
