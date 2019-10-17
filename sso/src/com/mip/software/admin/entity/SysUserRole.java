package com.mip.software.admin.entity;

import java.io.Serializable;


public class SysUserRole implements Serializable{
	
	/**
	  * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	  */
	private static final long serialVersionUID = 8786972517137834769L;
	private Long userid;
	private Long roleid;
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
}
