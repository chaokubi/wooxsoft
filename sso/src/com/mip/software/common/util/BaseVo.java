package com.mip.software.common.util;

import java.io.Serializable;

public class BaseVo implements Serializable{
	
	/**
	  * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	  */
	private static final long serialVersionUID = 1344502276473321321L;
	
	private Long id;
	
	private Integer delete_status;
	
	private String createDate;
	
	private String updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDelete_status() {
		return delete_status;
	}

	public void setDelete_status(Integer deleteStatus) {
		delete_status = deleteStatus;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}
