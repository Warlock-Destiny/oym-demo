package com.oym.common.auth.model;

import com.oym.base.db.entity.BaseCommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: 权限实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseCommonEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 父级id，一级菜单为0
	 */
	private Long parentId;

	/**
	 * 父级菜单名称
	 */
	private String parentName;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单url
	 */
	private String url;

	/**
	 * 类型(0：目录   1：菜单   2：按钮)
	 */
	private Integer type;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 排序
	 */
	private Integer orderNum;


}
