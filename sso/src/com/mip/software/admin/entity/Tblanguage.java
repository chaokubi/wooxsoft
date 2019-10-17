package com.mip.software.admin.entity;

import java.io.Serializable;

import com.mip.software.common.util.BaseVo;

/**
 * 语种
 * @author mip
 * 2016 Mar 15, 2016 1:21:45 AM
 */
public class Tblanguage extends BaseVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1607964913569481563L;
	private String name;
	private String code;
	private String baseFee;
	private String state;
	private String remark;
	private Long userid;
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBaseFee() {
		return baseFee;
	}
	public void setBaseFee(String baseFee) {
		this.baseFee = baseFee;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
