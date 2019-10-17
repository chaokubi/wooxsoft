package com.mip.software.common.page;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页查询类
 * @author zhaozhenghua
 */
public class PageQuery {
	
	/**
	 * 存放条件查询值
	 */
	private Map<String, Object> queryCondition;
	
	/**
	 * 存放分页信息
	 */
	private Pagination pagination;
	
	public PageQuery() {
		queryCondition = new HashMap<String, Object>();
		pagination = new Pagination();
	}

	public Map<String, Object> getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(Map<String, Object> queryCondition) {
		this.queryCondition = queryCondition;
	}
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
}
