package com.cn.zyd.base.db.pagination;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.zyd.base.db.constant.SortEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zyd
 * @date 2019/11/5 15:40
 * @desc 分页模型扩展类
 */
public class PageExt<T> extends Page<T> {

    public PageExt() {
    }

    public PageExt(long current, long size) {
        super(current, size);
    }

    public PageExt(long current, long size, long total) {
        super(current, size, total);
    }

    public PageExt(long current, long size, boolean isSearchCount) {
        super(current, size, isSearchCount);
    }

    public PageExt(long current, long size, long total, boolean isSearchCount) {
        super(current, size, total, isSearchCount);
    }

    public PageExt addOrder(String column, String sort) {
        if (StringUtils.isEmpty(sort)) {
            return this;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn(column);
        orderItem.setAsc(SortEnum.isAsc(sort));
        super.addOrder(orderItem);
        return this;
    }
}
