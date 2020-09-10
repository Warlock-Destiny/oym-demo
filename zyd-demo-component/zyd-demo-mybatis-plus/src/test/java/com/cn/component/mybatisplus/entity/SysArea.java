package com.cn.component.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *  行政区划
 */
@Data
@Accessors(chain = true)
@TableName("t_sys_area")
public class SysArea extends BaseCommonEntity {

    public static final String AREA_CODE = "area_code";
    public static final String PARENT_CODE = "parent_code";
    public static final String NAME = "name";
    public static final String LAYER = "layer";
    public static final String ORDER_NUM = "order_num";
    public static final String STATUS = "status";
    public static final String REMARK = "remark";

    // 行政区划代码
    @TableField(AREA_CODE)
    private String areaCode;
    // 父级id
    @TableField(PARENT_CODE)
    private String parentCode;
    // 地区名称
    @TableField(NAME)
    private String name;
    // 层级
    @TableField(LAYER)
    private Integer layer;
    // 排序号,1:省级,2:地市,3:区县
    @TableField(ORDER_NUM)
    private Integer orderNum;
    // 显示,1:显示,0:隐藏
    @TableField(STATUS)
    private Integer status;
    // 备注
    @TableField(REMARK)
    private String remark;
}
